package backend.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.dto.MedicineDTO;
import backend.models.Medicine;
import backend.models.Pharmacy;
import backend.models.PharmacyMedicines;
import backend.repositories.PharmacyMedicinesRepository;
import backend.services.IPharmacyMedicinesService;
import backend.services.IService;

@Service
public class PharmacyMedicinesService implements IPharmacyMedicinesService {

	@Autowired
	private PharmacyMedicinesRepository pharmacyMedicineRepository;
	
	@Override
	public List<PharmacyMedicines> findAll() {
		return pharmacyMedicineRepository.findAll();
	}

	@Override
	public PharmacyMedicines findById(Long id) {
		return pharmacyMedicineRepository.findById(id).orElseGet(null);
	}

	@Override
	public void save(PharmacyMedicines obj) {
		pharmacyMedicineRepository.save(obj);
	}

	@Override
	public void delete(PharmacyMedicines obj) {
		pharmacyMedicineRepository.delete(obj);
	}
	
	@Override
	public List<Medicine> findAllMedicinesInPharmacy(Long pharmacyId) {
		List<PharmacyMedicines> pmList = pharmacyMedicineRepository.findByPharmacyIdEquals(pharmacyId);
		if (pmList.size() == 0) {
			return null;
		}
		
		List<Medicine> medicines = new ArrayList<Medicine>();
		for (PharmacyMedicines pm : pmList) {
			Medicine medicine = pm.getMedicine();
			medicines.add(medicine);
		}
		
		return medicines;
	}
	
	@Override
	public List<Pharmacy> findAllPharmaciesWithMedicine(Long medicineId) {
		List<PharmacyMedicines> pmList = pharmacyMedicineRepository.findByMedicineIdEquals(medicineId);
		if (pmList.size() == 0) {
			return null;
		}
		
		List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
		for (PharmacyMedicines pm : pmList) {
			Pharmacy pharmacy = pm.getPharmacy();
			pharmacies.add(pharmacy);
		}
		
		return pharmacies;
		
	}

}
