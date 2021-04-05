package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import backend.models.Visit;
import backend.services.impl.VisitService;

@RestController
@RequestMapping(value = "appointments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VisitController {

	@Autowired
	private VisitService visitService;
	private static Gson g = new Gson();
	
	@PostMapping(value = "/make-appointment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> makeAppointment(@RequestBody Visit newReservation){
		
		visitService.save(newReservation);
		System.out.println("We got " + newReservation + " from client...");
		
		return new ResponseEntity<String>(g.toJson(newReservation), HttpStatus.OK);
	}
	
}
