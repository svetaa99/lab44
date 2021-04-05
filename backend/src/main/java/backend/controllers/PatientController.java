package backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import backend.dto.PatientDTO;
import backend.models.Patient;
import backend.services.impl.PatientService;

@RestController
@RequestMapping(value = "patients")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientController {

	@Autowired
	private PatientService patientService;
	private static Gson g = new Gson();
	
	@GetMapping("/all")
	public ResponseEntity<String> getPatients() {
		System.out.println("Returning patients...");
		
		List<Patient> retVal = patientService.findAll();
		
		List<PatientDTO> patientsDTO = new ArrayList<>();
		for (Patient p : retVal) {
			patientsDTO.add(new PatientDTO(p));
		}

		return new ResponseEntity<String>(g.toJson(patientsDTO), HttpStatus.OK);
	}
	@GetMapping("/{name}")
	public ResponseEntity<String> getPatientsByName(@PathVariable String name){
		System.out.println("Returning patients searched by name...");
		List<Patient> retVal = patientService.findAllByName(name);
		
		List<PatientDTO> patientsDTO = new ArrayList<>();
		for (Patient p : retVal) {
			patientsDTO.add(new PatientDTO(p));
		}
		
		return new ResponseEntity<String>(g.toJson(patientsDTO), HttpStatus.OK);
	}
}
