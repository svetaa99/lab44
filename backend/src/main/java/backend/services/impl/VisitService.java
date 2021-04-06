package backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.Visit;
import backend.repositories.VisitRepository;
import backend.services.IService;

@Service
public class VisitService implements IService<Visit>{

	@Autowired
	private VisitRepository visitRepository;

	@Override
	public List<Visit> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Visit findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Visit> findByDoctorIdEquals(Long doctorId){
		return visitRepository.findByDoctorIdEquals(doctorId);
	}
	
	public List<Visit> findByPatientIdEquals(Long patientId){
		return visitRepository.findByPatientIdEquals(patientId);
	}
	
	@Override
	public Visit save(Visit obj) {
		return visitRepository.save(obj);
	}

	@Override
	public void delete(Visit obj) {
		// TODO Auto-generated method stub
		
	}
	
}
