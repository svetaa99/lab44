package backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.Pharmacy;
import backend.repositories.PharmacyRepository;

@Service
public class PharmacyService {
	
	@Autowired
	private PharmacyRepository pharmacyRepository;
	
	public List<Pharmacy> findAll() {
		return pharmacyRepository.findAll();
	}
	
	public Pharmacy findById(Long id) {
		return pharmacyRepository.findById(id).orElseGet(null);
	}
	
	public void save(Pharmacy lab) { 
		pharmacyRepository.save(lab);
	}
	
	public void delete(Pharmacy lab) {
		pharmacyRepository.delete(lab);
	}

}
