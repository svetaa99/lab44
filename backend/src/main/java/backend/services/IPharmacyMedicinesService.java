package backend.services;

import java.util.List;

import backend.models.Medicine;
import backend.models.Pharmacy;
import backend.models.PharmacyMedicines;

public interface IPharmacyMedicinesService extends IService<PharmacyMedicines> {
	
	List<Medicine> findAllMedicinesInPharmacy(Long pharmacyId);
	
	List<Pharmacy> findAllPharmaciesWithMedicine(Long medicineId);
	
	PharmacyMedicines findPharmacyMedicinesByIds(Long pharmacyId, Long medicineId);
	
	List<PharmacyMedicines> findAllByPharmacyId(Long pharmacyId);
	
	List<PharmacyMedicines> findAllByMedicineId(Long medicineId);
	
	List<PharmacyMedicines> findAllByMedicineName(String medicineName);
	
	List<PharmacyMedicines> findByMedicineIdAndTodaysDate(Long medicineId, long todaysDate);
	
	List<PharmacyMedicines> findByPharmacyIdAndTodaysDate(Long pharmacyId, long todaysDate);
	
	List<PharmacyMedicines> findByMedicineNameAndTodaysDate(String medicineName, long todaysDate);
	
	PharmacyMedicines findByPharmacyIdAndMedicineIdAndTodaysDate(Long pharmacyId, Long medicineId, long todaysDate);
}
