package backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.dto.PharmacyDTO;
import backend.dto.PharmacyMedicinesDTO;
import backend.models.Medicine;
import backend.models.Pharmacy;
import backend.models.PharmacyMedicineAddRemoveObject;
import backend.models.PharmacyMedicines;
import backend.services.IMedicineService;
import backend.services.IPharmacyMedicinesService;
import backend.services.IPharmacyService;
import backend.services.impl.MedicineService;
import backend.services.impl.PharmacyMedicinesService;
import backend.services.impl.PharmacyService;

@RestController
@RequestMapping(value = "pharmacies")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacyController {

	@Autowired
	private IPharmacyService pharmacyService;
	
	@Autowired
	private IMedicineService medicineService;
	
	@Autowired
	private IPharmacyMedicinesService pmService;
	
	private List<PharmacyDTO> createPharmacyDTOList(List<Pharmacy> pharmacies) {
		List<PharmacyDTO> pharmaciesDTO = new ArrayList<PharmacyDTO>();
		
		for (Pharmacy pharmacy : pharmacies) {
			PharmacyDTO pharmacyDTO = new PharmacyDTO(pharmacy);
			pharmaciesDTO.add(pharmacyDTO);
		}
		
		return pharmaciesDTO;
	}
	
	@GetMapping("/all")
	private ResponseEntity<List<PharmacyDTO>> getAllPharmacies() {
		List<Pharmacy> pharmacies = pharmacyService.findAll();
		List<PharmacyDTO> pharmaciesDTO = createPharmacyDTOList(pharmacies);
		
		return new ResponseEntity<List<PharmacyDTO>>(pharmaciesDTO, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<PharmacyDTO> getById(@PathVariable Long id) {
		Pharmacy pharmacy = pharmacyService.findById(id);
		if (pharmacy.equals(null)) {
			return new ResponseEntity<PharmacyDTO>(HttpStatus.NOT_FOUND);
		}

		PharmacyDTO pharmacyDTO = new PharmacyDTO(pharmacy);
		
		return new ResponseEntity<PharmacyDTO>(pharmacyDTO, HttpStatus.OK);
	}
	
	@GetMapping("/search/{name}")
	private ResponseEntity<List<PharmacyDTO>> getAllByName(@PathVariable String name) {
		List<Pharmacy> pharmacies = (List<Pharmacy>) pharmacyService.findAllByName(name);
		List<PharmacyDTO> pharmaciesDTO = createPharmacyDTOList(pharmacies);
		
		return new ResponseEntity<List<PharmacyDTO>>(pharmaciesDTO, HttpStatus.OK);
	}
	
	@GetMapping("/filter/{rating}")
	private ResponseEntity<List<PharmacyDTO>> getAllByRate(@PathVariable double rating) {
		List<Pharmacy> pharmacies = (List<Pharmacy>) pharmacyService.findAllByRating(rating);
		List<PharmacyDTO> pharmaciesDTO = createPharmacyDTOList(pharmacies);
		
		return new ResponseEntity<List<PharmacyDTO>>(pharmaciesDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/add-medicine", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<PharmacyMedicinesDTO> addMedicineToPharmacy(@RequestBody PharmacyMedicineAddRemoveObject obj) {
		Pharmacy pharmacy = pharmacyService.findById(obj.getPharmacyId());
		if (pharmacy.equals(null)) {
			return new ResponseEntity<PharmacyMedicinesDTO>(HttpStatus.NOT_FOUND);
		}
		
		Medicine medicine = medicineService.findById(obj.getMedicineId());
		if (medicine.equals(null)) {
			return new ResponseEntity<PharmacyMedicinesDTO>(HttpStatus.NOT_FOUND);
		}
		int quantity = obj.getQuantity();
		
		PharmacyMedicines pm = new PharmacyMedicines();
		pm.setPharmacy(pharmacy);
		pm.setMedicine(medicine);
		pm.setQuantity(quantity);
		
		pm = pmService.save(pm);
		return new ResponseEntity<PharmacyMedicinesDTO>(new PharmacyMedicinesDTO(pm), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/delete-medicine")
	private ResponseEntity<PharmacyMedicinesDTO> deleteMedicineFromPharmacy(@RequestBody PharmacyMedicineAddRemoveObject obj) {	
		PharmacyMedicines pm = pmService.findPharmacyMedicinesByIds(obj.getPharmacyId(), obj.getMedicineId());
		pmService.delete(pm);
		
		return new ResponseEntity<PharmacyMedicinesDTO>(HttpStatus.OK);
	}
	
	@PutMapping(value = "/update-quantity")
	private ResponseEntity<PharmacyMedicinesDTO> updateMedicineQuantityInPharmacy(@RequestBody PharmacyMedicineAddRemoveObject obj) {
		PharmacyMedicines pm = pmService.findPharmacyMedicinesByIds(obj.getPharmacyId(), obj.getMedicineId());
		pm.setQuantity(obj.getQuantity());
		
		pm = pmService.save(pm);
		return new ResponseEntity<PharmacyMedicinesDTO>(new PharmacyMedicinesDTO(pm), HttpStatus.OK);
	}
	
}
