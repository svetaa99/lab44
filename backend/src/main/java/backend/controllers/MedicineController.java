package backend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.dto.MedicineDTO;
import backend.models.Medicine;
import backend.models.Patient;
import backend.models.PharmacyMedicines;
import backend.models.Visit;
import backend.services.IMedicineService;
import backend.services.IPharmacyMedicinesService;
import backend.services.impl.PatientService;
import backend.services.impl.VisitService;

@RestController
@RequestMapping(value = "medicines")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicineController {
	
	@Autowired
	private IMedicineService medicineService;
	
	@Autowired
	private VisitService visitService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private IPharmacyMedicinesService pharmMedService;
	
	private List<MedicineDTO> createMedicineDTOList(List<Medicine> medicines) {
		List<MedicineDTO> medicinesDTO = new ArrayList<MedicineDTO>();
		
		for (Medicine medicine : medicines) {
			MedicineDTO medicineDTO = new MedicineDTO(medicine);
			medicinesDTO.add(medicineDTO);
		}
		
		return medicinesDTO;
	}

	@GetMapping("/all")
	public ResponseEntity<List<MedicineDTO>> getAllMedicines() {
		List<Medicine> medicines = medicineService.findAll();
		List<MedicineDTO> medicinesDTO = createMedicineDTOList(medicines);
		
		return new ResponseEntity<List<MedicineDTO>>(medicinesDTO, HttpStatus.OK);
	}
	
	//method is used for visits
	@GetMapping("/all/{visitId}") 
	public ResponseEntity<List<MedicineDTO>> getAllMedicinesInPharmacy(@PathVariable Long visitId){
		Visit v = visitService.findById(visitId);
		Long pharmacyId = v.getPharmacy();
		
		List<PharmacyMedicines> pharmMedicines = pharmMedService.findAllByPharmacyId(pharmacyId);
		
		List<Medicine> medicines = new ArrayList<Medicine>();
		pharmMedicines.forEach(med -> medicines.add(med.getMedicine()));
		
		List<MedicineDTO> medicinesDTO = createMedicineDTOList(medicines);
		
		return new ResponseEntity<List<MedicineDTO>>(medicinesDTO, HttpStatus.OK);
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<List<MedicineDTO>> getAllByName(@PathVariable String name) {
		List<Medicine> medicines = (List<Medicine>) medicineService.findAllByName(name);
		List<MedicineDTO> medicinesDTO = createMedicineDTOList(medicines);
		
		return new ResponseEntity<List<MedicineDTO>>(medicinesDTO, HttpStatus.OK);
	}
	
	//method is used for visits
	@GetMapping("/search/{name}/{visitId}")
	public ResponseEntity<List<MedicineDTO>> getAllByNameInPharmacy(@PathVariable String name, @PathVariable Long visitId) {
		Visit v = visitService.findById(visitId);
		Long pharmacyId = v.getPharmacy();
		
		List<PharmacyMedicines> pharmMedicines = pharmMedService.findByMedicineNameAndPharmacyId(name, pharmacyId);
		
		List<Medicine> medicines = new ArrayList<Medicine>();
		pharmMedicines.forEach(med -> medicines.add(med.getMedicine()));
		
		List<MedicineDTO> medicinesDTO = createMedicineDTOList(medicines);
		
		return new ResponseEntity<List<MedicineDTO>>(medicinesDTO, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MedicineDTO> getById(@PathVariable Long id) {
		Medicine medicine = medicineService.findById(id);
		if (medicine.equals(null)) {
			return new ResponseEntity<MedicineDTO>(HttpStatus.NOT_FOUND);
		}
		
		MedicineDTO medicineDTO = new MedicineDTO(medicine);
		
		return new ResponseEntity<MedicineDTO>(medicineDTO, HttpStatus.OK);
	}
	@GetMapping("/substitute/{id}/{visitId}")
	public ResponseEntity<List<MedicineDTO>> getSubstituteForMedicine(@PathVariable Long id, @PathVariable Long visitId){
		List<Medicine> substituteList = medicineService.getSubstituteForMedicine(id);
		
		//filter this list so there is no allergens or unavailable medicine
		Visit v = visitService.findById(visitId);
		Patient p = patientService.findById(v.getPatientId());
		List<Medicine> allergies = p.getAllergies();
		
		List<PharmacyMedicines> available = pharmMedService.findAvailableByPharmacyId(v.getPharmacy());
		List<Medicine> availableMedicine = new ArrayList<Medicine>();
		available.forEach(m -> availableMedicine.add(m.getMedicine()));
		
		List<Medicine> filteredList = 
				substituteList
				.stream()
				.filter(med -> !allergies.contains(med) && availableMedicine.contains(med))
				.collect(Collectors.toList());
		
		List<MedicineDTO> dtoList = createMedicineDTOList(filteredList);
		
		return new ResponseEntity<List<MedicineDTO>>(dtoList, HttpStatus.OK);
	}
	
}
