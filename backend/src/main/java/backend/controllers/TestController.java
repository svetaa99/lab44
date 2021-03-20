package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.services.TestService;

@RestController
public class TestController {
	
	@Autowired
	private TestService service;
	
	@GetMapping("/test/hello")
	public ResponseEntity<String> hello() {
		System.out.println("ASDFDSAFSDAFSA");
		return new ResponseEntity<String>(service.sayHello(), HttpStatus.OK);
	}
}
