package backend.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.User;
import backend.repositories.DoctorRepository;
import backend.repositories.PatientRepository;
import backend.services.IService;

@Service
public class UserService implements IService<User>{
	
	@Autowired 
	private PatientRepository patientRepo;
	
	@Autowired
	private DoctorRepository doctorRepo;
	
	@Override
	public List<User> findAll() {
		List<User> allUsers = new ArrayList<User>();
		allUsers.addAll(patientRepo.findAll());
		allUsers.addAll(doctorRepo.findAll());
		return allUsers;
	}
	
	public User findUserByEmail(String email){
		return this.findAll().stream().filter(u -> u.getEmail().equals(email)).collect(Collectors.toList()).get(0);
	}
	@Override
	public User findById(Long id) {
		return null;
	}

	@Override
	public User save(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User obj) {
		// TODO Auto-generated method stub
		
	}

}
