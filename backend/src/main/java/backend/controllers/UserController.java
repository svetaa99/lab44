package backend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import backend.models.User;

@RestController
@RequestMapping(value = "users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	private static Gson g = new Gson(); 
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		User u = new User("Uros", "Petric", "urke@gmail.com", "ukica123", Long.parseLong("1") , "12345678");
		return u;
	}
}
