package backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.Visit;
import backend.repositories.VisitRepository;

@Service
public class VisitService implements ServiceInterface<Visit>{

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

	@Override
	public void save(Visit obj) {
		visitRepository.save(obj);
	}

	@Override
	public void delete(Visit obj) {
		// TODO Auto-generated method stub
		
	}
	
}
