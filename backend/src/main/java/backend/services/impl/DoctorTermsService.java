package backend.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.enums.Status;
import backend.models.DoctorTerms;
import backend.models.Visit;
import backend.repositories.DoctorTermsRepository;
import backend.services.IService;

@Service
public class DoctorTermsService implements IService<DoctorTerms>{

	@Autowired
	private DoctorTermsRepository doctorTermsRepository;
	
	@Autowired
	private VisitService visitService;

	@Override
	public List<DoctorTerms> findAll() {
		return doctorTermsRepository.findAll();
	}

	@Override
	public DoctorTerms findById(Long id) {
		return doctorTermsRepository.findById(id).orElse(null);
	}
	
	public List<DoctorTerms> findByDoctorIdEquals(Long id){
		return doctorTermsRepository.findByDoctorIdEquals(id);
	}

	@Transactional
	@Override
	public DoctorTerms save(DoctorTerms obj) {
		return doctorTermsRepository.save(obj);
	}

	@Override
	public void delete(DoctorTerms obj) {
		doctorTermsRepository.delete(obj);
	}
	
	public void makeReservation(Long termId, Long patientId) {
		DoctorTerms doctorTerm = findById(termId);
		
		// Make appointment
		Visit newVisit = new Visit();
		newVisit.setPatientId(patientId);
		newVisit.setDoctorId(doctorTerm.getDoctorId());
		newVisit.setStart(doctorTerm.getStart());
		newVisit.setFinish(doctorTerm.getFinish());
		newVisit.setPharmacy(doctorTerm.getPharmacyId());
		newVisit.setStatus(Status.RESERVED);
		
		visitService.save(newVisit);
		
		delete(doctorTerm);
	}
	public List<DoctorTerms> findAllFutureTerms(){
		return doctorTermsRepository.findAll().stream().filter(dt -> dt.getStart().isAfter(LocalDateTime.now())).collect(Collectors.toList());
	}
	
}
