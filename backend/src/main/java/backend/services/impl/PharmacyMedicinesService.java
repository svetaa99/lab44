package backend.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import backend.models.Medicine;
import backend.models.Pharmacy;
import backend.models.PharmacyMedicines;
import backend.models.Reservation;
import backend.repositories.PharmacyMedicinesRepository;
import backend.services.IPharmacyMedicinesService;

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
		return pharmacyMedicineRepository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public PharmacyMedicines save(PharmacyMedicines obj) {
		return pharmacyMedicineRepository.save(obj);
	}

	@Override
	public void delete(PharmacyMedicines obj) {
		pharmacyMedicineRepository.delete(obj);
	}
	
	@Override
	public List<Medicine> findAllMedicinesInPharmacy(Long pharmacyId) {
		List<PharmacyMedicines> pmList = pharmacyMedicineRepository.findByPharmacyIdEquals(pharmacyId);
		if (pmList.size() == 0) {
			return new ArrayList<Medicine>();
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
			return new ArrayList<Pharmacy>();
		}
		
		List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
		for (PharmacyMedicines pm : pmList) {
			Pharmacy pharmacy = pm.getPharmacy();
			pharmacies.add(pharmacy);
		}
		
		return pharmacies;
		
	}

	@Override
	public PharmacyMedicines findPharmacyMedicinesByIds(Long pharmacyId, Long medicineId) {
		return pharmacyMedicineRepository.findByPharmacyIdAndMedicineId(pharmacyId, medicineId);
	}

	@Override
	public List<PharmacyMedicines> findAllByPharmacyId(Long pharmacyId) {
		return pharmacyMedicineRepository.findByPharmacyIdEquals(pharmacyId);
	}

	@Override
	public List<PharmacyMedicines> findAllByMedicineId(Long medicineId) {
		return pharmacyMedicineRepository.findByMedicineIdEquals(medicineId);
	}
	
	@Override
	public List<PharmacyMedicines> findAllByMedicineName(String medicineName) {
		return pharmacyMedicineRepository.findByMedicineNameContainingIgnoreCase(medicineName);
	}

	@Override
	public List<PharmacyMedicines> findByMedicineIdAndTodaysDate(Long medicineId, long todaysDate) {
		return pharmacyMedicineRepository.findByMedicineIdAndTodaysDate(medicineId, todaysDate);
	}

	@Override
	public List<PharmacyMedicines> findByPharmacyIdAndTodaysDate(Long pharmacyId, long todaysDate) {
		return pharmacyMedicineRepository.findByPharmacyIdAndTodaysDate(pharmacyId, todaysDate);
	}

	@Override
	public List<PharmacyMedicines> findByMedicineNameAndTodaysDate(String medicineName, long todaysDate) {
		return pharmacyMedicineRepository.findByMedicineNameAndTodaysDate(medicineName, todaysDate);
	}

	@Transactional
	@Override
	public PharmacyMedicines findByPharmacyIdAndMedicineIdAndTodaysDate(Long pharmacyId, Long medicineId,
			long todaysDate) {
		return pharmacyMedicineRepository.findByPharmacyIdAndMedicineIdAndTodaysDate(pharmacyId, medicineId, todaysDate);
	}
	
	@Override
	public List<PharmacyMedicines> findAvailableByPharmacyId(Long pharmacyId) {
		return pharmacyMedicineRepository.findAvailableByPharmacyId(pharmacyId);

	}
	
	@Override
	public List<PharmacyMedicines> findByPharmacyAndMedicineNameAndTodaysDate(Long pharmacyId, String medicineName,
			long todaysDate) {
		return pharmacyMedicineRepository.findByPharmacyAndMedicineNameAndTodaysDate(pharmacyId, medicineName, todaysDate);

	}

	@Override
	public List<PharmacyMedicines> findByMedicineNameAndPharmacyId(String medicineName, Long pharmacyId) {
		// TODO Auto-generated method stub
		return pharmacyMedicineRepository.findByMedicineNameAndPharmacyId(medicineName, pharmacyId);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public PharmacyMedicines updateAfterReservation(Reservation reservation, int quantity) {
		PharmacyMedicines pm = findByPharmacyIdAndMedicineIdAndTodaysDate(reservation.getPharmacy().getId(), reservation.getMedicine().getId(), new Date().getTime());
		int oldQuantity = pm.getQuantity();
		if (oldQuantity < quantity) {
			return null;
		}
		
		int newQuantity = oldQuantity - quantity;
		
		pm.setQuantity(newQuantity);
		pm = save(pm);
		
		return pm;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public PharmacyMedicines updateAfterReservationCancel(Reservation reservation) {
		PharmacyMedicines pm = findPharmacyMedicinesByIds(reservation.getPharmacy().getId(), reservation.getMedicine().getId());
		if (pm == null) {
			return null;
		}
		
		int oldQuantity = pm.getQuantity();
		
		int newQuantity = oldQuantity + reservation.getQuantity();
		pm.setQuantity(newQuantity);
		pm = save(pm);
		return pm;
	}
	

}
