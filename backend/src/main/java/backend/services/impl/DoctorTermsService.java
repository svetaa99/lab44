package backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.DoctorTerms;
import backend.models.WorkHours;
import backend.repositories.DoctorTermsRepository;
import backend.repositories.WorkHoursRepository;
import backend.services.IService;

@Service
public class DoctorTermsService implements IService<DoctorTerms>{

	@Autowired
	private DoctorTermsRepository doctorTermsRepository;
	
	@Autowired
	private WorkHoursRepository workHoursRepository;

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
	
	public List<WorkHours> findWorkingHoursForDoctorByIdAndPharmacyId(Long doctorId, Long pharmacyId){
		return workHoursRepository.findByDoctorIdAndPharmacyId(doctorId, pharmacyId);
	}
	
	
}
