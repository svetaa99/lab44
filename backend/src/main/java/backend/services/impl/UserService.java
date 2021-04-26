package backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.User;
import backend.repositories.UserRepository;
import backend.services.IService;

@Service
public class UserService implements IService<User>{
	
	@Autowired 
	private UserRepository userService;
	
	@Override
	public List<User> findAll() {
//		List<User> allUsers = new ArrayList<User>();
//		allUsers.addAll(patientRepo.findAll());
//		allUsers.addAll(doctorRepo.findAll());
		for (User u : userService.findAll()) {
			System.out.println(u);
		}
		return userService.findAll();
	}
	
	public User findUserByEmail(String email){
		return userService.findUserByEmailEquals(email).get(0);
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
