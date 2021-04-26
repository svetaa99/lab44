package backend.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
		return visitRepository.findById(id).orElse(null);
	}
	
	public List<Visit> findByDoctorIdEquals(Long doctorId){
		return visitRepository.findByDoctorIdEquals(doctorId);
	}
	
	public List<Visit> findByPatientIdEquals(Long patientId){
		return visitRepository.findByPatientIdEquals(patientId);
	}
	
	public LocalDateTime lastVisitByPatientIdEquals(Long patientId) {
		List<Visit> allPatientsVisits = visitRepository.findByPatientIdEquals(patientId);

		if(allPatientsVisits.size() == 0)
			return null;
		
		LocalDateTime max = allPatientsVisits.get(0).getStart();
		for (Visit visit : allPatientsVisits) {
			if(visit.getStart().isAfter(max))
				max = visit.getStart();
		}
		return max;
	}
	
	public List<Visit> findByDoctorIdFuture(Long doctorId){
		List<Visit> allDoctorVisits = visitRepository.findByDoctorIdEquals(doctorId);
		
		return allDoctorVisits.stream().filter(v -> v.getFinish().isAfter(LocalDateTime.now())).collect(Collectors.toList());
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
