package backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.Medicine;
import backend.repositories.MedicineRepository;
import backend.services.IMedicineService;
import backend.services.IService;

@Service
public class MedicineService implements IMedicineService{

	@Autowired
	private MedicineRepository medicineRepository;

	@Override
	public List<Medicine> findAll() {
		return medicineRepository.findAll();
	}
	
	@Override
	public List<Medicine> findAllByName(String name) {
		return medicineRepository.findAllByNameContainingIgnoreCase(name);
	}

	@Override
	public Medicine findById(Long id) {
		return medicineRepository.findById(id).orElseGet(null);
	}

	@Override
	public void save(Medicine obj) {
		medicineRepository.save(obj);
	}

	@Override
	public void delete(Medicine obj) {
		medicineRepository.delete(obj);
	}
	
	
	
}
