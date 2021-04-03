package backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.Medicine;
import backend.repositories.MedicineRepository;

@Service
public class MedicineService implements ServiceInterface<Medicine>{

	@Autowired
	private MedicineRepository medicineRepository;

	@Override
	public List<Medicine> findAll() {
		return medicineRepository.findAll();
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
