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
	
	@GetMapping(value = "/get-medicines/{pharmacyId}")
	public ResponseEntity<List<PharmacyMedicinesDTO>> getPMFromPharmacyId(@PathVariable Long pharmacyId) {
		List<PharmacyMedicines> pmList = pharmacyMedicineService.findAllByPharmacyId(pharmacyId);
		List<PharmacyMedicinesDTO> pmDTOs = new ArrayList<PharmacyMedicinesDTO>();
		
		for (PharmacyMedicines pm : pmList) {
			PharmacyMedicinesDTO pmDTO = new PharmacyMedicinesDTO(pm);
			pmDTOs.add(pmDTO);
		}
		
		return new ResponseEntity<List<PharmacyMedicinesDTO>>(pmDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-pharmacies/{medicineId}")
	public ResponseEntity<List<PharmacyMedicinesDTO>> getPMFromMedicineId(@PathVariable Long medicineId) {
		List<PharmacyMedicines> pmList = pharmacyMedicineService.findAllByMedicineId(medicineId);
		List<PharmacyMedicinesDTO> pmDTOs = new ArrayList<PharmacyMedicinesDTO>();
		
		for (PharmacyMedicines pm : pmList) {
			PharmacyMedicinesDTO pmDTO = new PharmacyMedicinesDTO(pm);
			pmDTOs.add(pmDTO);
		}
		
		return new ResponseEntity<List<PharmacyMedicinesDTO>>(pmDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-medicines-by-name/{medicineName}")
	public ResponseEntity<List<PharmacyMedicinesDTO>> getPMFromMedicineName(@PathVariable("medicineName") String medicineName) {
		System.out.println(medicineName);
		List<PharmacyMedicines> pmList = pharmacyMedicineService.findAllByMedicineName(medicineName);
		List<PharmacyMedicinesDTO> pmDTOs = new ArrayList<PharmacyMedicinesDTO>();
		
		for (PharmacyMedicines pm : pmList) {
			PharmacyMedicinesDTO pmDTO = new PharmacyMedicinesDTO(pm);
			pmDTOs.add(pmDTO);
		}
		
		return new ResponseEntity<List<PharmacyMedicinesDTO>>(pmDTOs, HttpStatus.OK);
	}
}
