package backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.services.PatientService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientController {
	
	private PatientService service = new PatientService();
	
	@GetMapping("/patients/all")
	public ResponseEntity<String> getPatients() {
		System.out.println("Returning patients...");
		return new ResponseEntity<String>(service.getAllPatients(), HttpStatus.OK);
	}
	
}
