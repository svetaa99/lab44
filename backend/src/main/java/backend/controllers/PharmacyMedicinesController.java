package backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.models.Medicine;
import backend.models.Pharmacy;
import backend.services.IPharmacyMedicinesService;
import backend.services.impl.PharmacyMedicinesService;

@RestController
@RequestMapping(value = "pharmacy-medicines")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacyMedicinesController {
	
	@Autowired
	private IPharmacyMedicinesService pharmacyMedicineService;
	
	@GetMapping(value = "/get-medicines/{id}")
	public List<Medicine> getMedicinesFromPharmacy(@PathVariable("id") Long pharmacyId) {
		return pharmacyMedicineService.findAllMedicinesInPharmacy(pharmacyId);
	}
	
	@GetMapping(value = "/get-pharmacies/{id}")
	public List<Pharmacy> getPharmaciesWithMedicine(@PathVariable Long medicineId) {
		return pharmacyMedicineService.findAllPharmaciesWithMedicine(medicineId);
	}
	
}
