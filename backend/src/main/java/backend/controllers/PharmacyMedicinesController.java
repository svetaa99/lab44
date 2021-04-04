package backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.models.Medicine;
import backend.models.Pharmacy;
import backend.services.PharmacyMedicinesService;

@RestController
@RequestMapping(value = "pharmacy-medicines")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacyMedicinesController {
	
	@Autowired
	private PharmacyMedicinesService pharmacyMedicineService;
	
	public List<Medicine> getMedicinesFromPharmacy(Long pharmacyId) {
		return pharmacyMedicineService.findAllMedicinesInPharmacy(pharmacyId);
	}
	
	public List<Pharmacy> getPharmaciesWithMedicine(Long medicineId) {
		return pharmacyMedicineService.findAllPharmaciesWithMedicine(medicineId);
	}
	
}
