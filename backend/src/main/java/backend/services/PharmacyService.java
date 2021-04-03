package backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.Pharmacy;
import backend.repositories.PharmacyRepository;

@Service
public class PharmacyService implements ServiceInterface<Pharmacy> {
	
	@Autowired
	private PharmacyRepository pharmacyRepository;
	
	public List<Pharmacy> findAll() {
		return pharmacyRepository.findAll();
	}
	
	public Pharmacy findById(Long id) {
		return pharmacyRepository.findById(id).orElseGet(null);
	}
	
	public List<Pharmacy> findAllByName(String name) {
		return pharmacyRepository.findAllByNameContainingIgnoreCase(name);
	}
	
	public void save(Pharmacy pharmacy) { 
		pharmacyRepository.save(pharmacy);
	}
	
	public void delete(Pharmacy pharmacy) {
		pharmacyRepository.delete(pharmacy);
	}

}
