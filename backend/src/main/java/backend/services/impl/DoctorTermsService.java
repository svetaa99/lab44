package backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.DoctorTerms;
import backend.repositories.DoctorTermsRepository;
import backend.services.IService;

@Service
public class DoctorTermsService implements IService<DoctorTerms>{

	@Autowired
	private DoctorTermsRepository doctorTermsRepository;

	@Override
	public List<DoctorTerms> findAll() {
		return doctorTermsRepository.findAll();
	}

	@Override
	public DoctorTerms findById(Long id) {
		return doctorTermsRepository.findById(id).orElseGet(null);
	}
	
	public List<DoctorTerms> findByDoctorIdEquals(Long id){
		return doctorTermsRepository.findByDoctorIdEquals(id);
	}

	@Override
	public DoctorTerms save(DoctorTerms obj) {
		return doctorTermsRepository.save(obj);
	}

	@Override
	public void delete(DoctorTerms obj) {
		doctorTermsRepository.delete(obj);
	}
	
	
}
