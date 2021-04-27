package backend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import backend.models.Medicine;
import backend.models.MedicineDays;
import backend.models.MedicineReportDTO;
import backend.models.Patient;
import backend.models.PharmacyMedicines;
import backend.models.Report;
import backend.models.Visit;
import backend.services.IMedicineService;
import backend.services.IPharmacyMedicinesService;
import backend.services.impl.PatientService;
import backend.services.impl.ReportService;
import backend.services.impl.VisitService;


@RestController
@RequestMapping(value = "reports")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@Autowired
	private IPharmacyMedicinesService pmService;
	
	@Autowired
	private IMedicineService medicineService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private VisitService visitService;

	//private static Gson g = new Gson();
	
	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST')")
	public ResponseEntity<List<MedicineReportDTO>> saveAppointment(@RequestBody Report newReport){
		
		//PharmacyMedicines pm = new PharmacyMedicines(); 
		// Visit has no field in which pharmacy it is held
		// Once that is changed in the model it will not be hardcoded to 1
		List<MedicineReportDTO> medDTO = new ArrayList<MedicineReportDTO>();
		
		Visit visitReport = visitService.findById(newReport.getVisitId());
		
		for (MedicineDays medDays : newReport.getMedicineDays()) {
			MedicineReportDTO newMedRepDTO = new MedicineReportDTO(medicineService.findById(medDays.getmedicine()));
			
			Long pharmacyId = 1l; //visitReport.getPharmacyId();
			newMedRepDTO.setAvailable(checkAvailable(pharmacyId, medDays.getmedicine())); //if not send notification to lab admin!
			
			Long patientId = visitReport.getPatientId();
			newMedRepDTO.setAllergic(checkAllergic(patientId, medDays.getmedicine())); //if true send ALTERNATIVES? to doctor
			
			medDTO.add(newMedRepDTO);
		}
		if(medDTO.stream().filter(m -> m.isAllergic() || !m.isAvailable()).collect(Collectors.toList()).size() > 0) {
			System.out.println("neuspesno");
		}
		else {
			System.out.println("uspesno");
			reportService.save(newReport);
		}
		return new ResponseEntity<List<MedicineReportDTO>>(medDTO, HttpStatus.OK);
	}
	private boolean checkAvailable(Long pharmacyId, Long medicineId) {
		PharmacyMedicines pm = pmService.findPharmacyMedicinesByIds(pharmacyId, medicineId);
		if(pm.getQuantity() <= 0)
			return false;
		else { //move this piece of code to the finish of report saving
			pm.setQuantity(pm.getQuantity() - 1);
			pmService.save(pm);
			return true;
		}
	}
	private boolean checkAllergic(Long patientId, Long medicineId) {
		Patient p = patientService.findById(patientId);
		for (Medicine allergy : p.getAllergies()) {
			if(medicineId == allergy.getId())
				return true;
		}
		return false;
	}
}
