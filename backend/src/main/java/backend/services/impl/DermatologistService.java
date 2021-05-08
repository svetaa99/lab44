package backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.Dermatologist;
import backend.repositories.DermatologistRepository;
import backend.services.IDermatologistService;

@Service
public class DermatologistService implements IDermatologistService {
	
	@Autowired
	private DermatologistRepository dermaRepository;

	@Override
	public List<Dermatologist> findAll() {
		return dermaRepository.findAll();
	}

	@Override
	public Dermatologist findById(Long id) {
		return dermaRepository.findById(id).orElse(null);
	}

	@Override
	public Dermatologist save(Dermatologist obj) {
		return dermaRepository.save(obj);
	}

	@Override
	public void delete(Dermatologist obj) {
		dermaRepository.delete(obj);
	}

}
