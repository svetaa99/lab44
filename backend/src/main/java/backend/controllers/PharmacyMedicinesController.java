package backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.dto.MedicineDTO;
import backend.dto.PharmacyDTO;
import backend.dto.PharmacyMedicinesDTO;
import backend.models.Medicine;
import backend.models.Pharmacy;
import backend.models.PharmacyMedicines;
import backend.services.IPharmacyMedicinesService;

@RestController
@RequestMapping(value = "pharmacy-medicines")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacyMedicinesController {
	
	@Autowired
	private IPharmacyMedicinesService pharmacyMedicineService;
	
	public List<Medicine> getMedicinesFromPharmacy(Long pharmacyId) {
		return pharmacyMedicineService.findAllMedicinesInPharmacy(pharmacyId);
	}
	   
	public List<Pharmacy> getPharmaciesWithMedicine(Long medicineId) {
		return pharmacyMedicineService.findAllPharmaciesWithMedicine(medicineId);
	}
	
	@GetMapping(value = "/get-medicines/{id}")
	public ResponseEntity<List<MedicineDTO>> getMedicnesDTOFromPharmacy(@PathVariable("id") Long pharmacyId) {
		List<Medicine> medicines = getMedicinesFromPharmacy(pharmacyId);
		List<MedicineDTO> medsDTO = new ArrayList<MedicineDTO>();
		
		for (Medicine medicine: medicines) {
			MedicineDTO m = new MedicineDTO(medicine);
			medsDTO.add(m);
		}
		
		return new ResponseEntity<List<MedicineDTO>>(medsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-pharmacies/{id}")
	public ResponseEntity<List<PharmacyDTO>> getPharmacyDTOFromMedicine(@PathVariable Long medicineId) {
		List<Pharmacy> pharmacies = getPharmaciesWithMedicine(medicineId);
		List<PharmacyDTO> pharmsDTO = new ArrayList<PharmacyDTO>();
		
		for (Pharmacy pharmacy : pharmacies) {
			PharmacyDTO p = new PharmacyDTO(pharmacy);
			pharmsDTO.add(p);
		}
		
		return new ResponseEntity<List<PharmacyDTO>>(pharmsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-medicine-state/{pharmacyId}")
	public ResponseEntity<List<PharmacyMedicinesDTO>> getPMFromPharmacyId(@PathVariable Long pharmacyId) {
		List<PharmacyMedicines> pmList = pharmacyMedicineService.findAllByPharmacyId(pharmacyId);
		List<PharmacyMedicinesDTO> pmDTOs = new ArrayList<PharmacyMedicinesDTO>();
		
		for (PharmacyMedicines pm : pmList) {
			PharmacyMedicinesDTO pmDTO = new PharmacyMedicinesDTO(pm);
			pmDTOs.add(pmDTO);
		}
		
		return new ResponseEntity<List<PharmacyMedicinesDTO>>(pmDTOs, HttpStatus.OK);
	}
	
}
