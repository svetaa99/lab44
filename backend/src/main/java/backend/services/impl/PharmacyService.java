package backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.Pharmacy;
import backend.repositories.PharmacyRepository;
import backend.services.IPharmacyService;
import backend.services.IService;

@Service
public class PharmacyService implements IPharmacyService {
	
	@Autowired
	private PharmacyRepository pharmacyRepository;
	
	@Override
	public List<Pharmacy> findAll() {
		return pharmacyRepository.findAll();
	}
	
	@Override
	public Pharmacy findById(Long id) {
		return pharmacyRepository.findById(id).orElseGet(null);
	}
	
	@Override
	public List<Pharmacy> findAllByName(String name) {
		return pharmacyRepository.findAllByNameContainingIgnoreCase(name);
	}
	
	@Override
	public Pharmacy save(Pharmacy pharmacy) { 
		return pharmacyRepository.save(pharmacy);
	}
	
	@Override
	public void delete(Pharmacy pharmacy) {
		pharmacyRepository.delete(pharmacy);
	}

}
