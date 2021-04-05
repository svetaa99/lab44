package backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.Patient;
import backend.repositories.PatientRepository;

@Service
public class PatientService implements ServiceInterface<Patient>{ 

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public List<Patient> findAll() {
		return patientRepository.findAll();
	}
	
	public List<Patient> findAllByName(String name){
		return patientRepository.findAllByName(name);
	}
	
	@Override
	public Patient findById(Long id) {
		return patientRepository.findById(id).orElseGet(null);
	}

	@Override
	public void save(Patient obj) {
		patientRepository.save(obj);
	}

	@Override
	public void delete(Patient obj) {
		patientRepository.delete(obj);
	}
	
	
	
}
