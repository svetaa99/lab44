package backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.Pharmacist;
import backend.models.Pharmacy;
import backend.repositories.PharmacistRepository;
import backend.services.IPharmacistService;

@Service
public class PharmacistService implements IPharmacistService {
	
	@Autowired
	private PharmacistRepository pharmacistRepository;

	@Override
	public List<Pharmacist> findAll() {
		return pharmacistRepository.findAll();
	}

	@Override
	public Pharmacist findById(Long id) {
		return pharmacistRepository.findById(id).orElseGet(null);
	}

	@Override
	public Pharmacist save(Pharmacist pharmacist) {
		return pharmacistRepository.save(pharmacist);
	}

	@Override
	public void delete(Pharmacist pharmacist) {
		pharmacistRepository.delete(pharmacist);
	}
	
	@Override
	public List<Pharmacist> findAllByPharmacy(Long pharmacistId) {
		return pharmacistRepository.findAllByPharmacyIdEquals(pharmacistId);
	}
	
	@Override
	public List<Pharmacist> sortByRating(String type, Long pharmacistId) {
		if (type.equals("asc")) {
			return pharmacistRepository.findAllByPharmacyIdOrderByRatingAsc(pharmacistId);
		} else {
			return pharmacistRepository.findAllByPharmacyIdOrderByRatingDesc(pharmacistId);
		}
	}
	
}
