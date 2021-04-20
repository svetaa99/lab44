package backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import backend.dto.UserTokenState;
import backend.models.Login;
import backend.models.User;
import backend.security.TokenUtils;
import backend.services.impl.UserService;

@RestController
@RequestMapping(value = "users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	//private static Gson g = new Gson(); 
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private UserService us;
	
	@GetMapping("/all")
	public List<User> getAllUsers() {
		return us.findAll();
	}
	
	@PostMapping(value="/login-user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserTokenState> login(@RequestBody Login loginData) {
		String jwt = "";
		if (authenticate(loginData)) {
			jwt = tokenUtils.generateToken(loginData.getEmail());
			int expiresIn = tokenUtils.getExpiredIn();
			return new ResponseEntity<UserTokenState>(new UserTokenState(jwt, expiresIn), HttpStatus.OK);
		}
		return new ResponseEntity<UserTokenState>(new UserTokenState(jwt, 0), HttpStatus.OK);
	}
	
	private boolean authenticate(Login loginData) {
		for (User user : us.findAll()) {
			if(loginData.getEmail().equals(user.getEmail())) {
				if(loginData.getPassword().equals(user.getPassword())) {
					return true;
				}
			}
		}
		return false;
	}
}
