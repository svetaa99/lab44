package backend.services.impl;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.WorkHours;
import backend.repositories.WorkHoursRepository;
import backend.services.IService;

@Service
public class WorkHoursService implements IService<WorkHours> {
	
	@Autowired
	private WorkHoursRepository workHoursRepository;
	
	public List<WorkHours> getPharmaciesByTime(LocalTime time) {
		
		List<WorkHours> workHours = workHoursRepository.findAll();
		
		return workHours.stream()
				.filter(wh -> wh.getStartTime().isBefore(time) && wh.getFinishTime().isAfter(time)).collect(Collectors.toList());
	}

	@Override
	public List<WorkHours> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorkHours findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorkHours save(WorkHours obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(WorkHours obj) {
		// TODO Auto-generated method stub
		
	}
}
