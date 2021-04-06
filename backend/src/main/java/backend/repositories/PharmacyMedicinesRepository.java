package backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.PharmacyMedicines;

public interface PharmacyMedicinesRepository extends JpaRepository<PharmacyMedicines, Long> {
	
	List<PharmacyMedicines> findByPharmacyIdEquals(Long pharmacyId);
	
	List<PharmacyMedicines> findByMedicineIdEquals(Long medicineId);
	
	PharmacyMedicines findByPharmacyIdEqualsAndMedicineIdEquals(Long pharmacyId, Long medicineId);
}
