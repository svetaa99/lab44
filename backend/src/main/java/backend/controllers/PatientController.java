package backend.controllers;

import java.time.LocalDateTime;
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
import backend.services.impl.VisitService;
import comparators.PatientDTOComparator;

@RestController
@RequestMapping(value = "patients")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private VisitService visitService;
	
	private static Gson g = new Gson();
	
	@GetMapping("/all")
	public ResponseEntity<String> getPatients() {
		System.out.println("Returning patients...");
		
		List<Patient> retVal = patientService.findAll();
		
		List<PatientDTO> patientsDTO = turnPatientsToDTO(retVal);

		return new ResponseEntity<String>(g.toJson(patientsDTO), HttpStatus.OK);
	}
	@GetMapping("/{name}")
	public ResponseEntity<String> getPatientsByName(@PathVariable String name){
		System.out.println("Returning patients searched by name...");
		List<Patient> retVal = patientService.findAllByName(name);
		
		List<PatientDTO> patientsDTO = new ArrayList<>();
		for (Patient p : retVal) {
			LocalDateTime lastVisit = visitService.lastVisitByPatientIdEquals(p.getId());
			if(lastVisit!=null)
				patientsDTO.add(new PatientDTO(p, lastVisit.toLocalDate()));
			else
				patientsDTO.add(new PatientDTO(p, null));
		}
		
		return new ResponseEntity<String>(g.toJson(patientsDTO), HttpStatus.OK);
	}
	@GetMapping("/{param}/{order}/{searchParam}")
	public ResponseEntity<String> getPatientsSorted(@PathVariable String param, @PathVariable int order, @PathVariable String searchParam){
		System.out.println("IDE GAS");
		List<Patient> patients = new ArrayList<Patient>();
		List<PatientDTO> patientsDTO = new ArrayList<PatientDTO>();
		
		if(param.equals("date")) {
			patients = patientService.findAll();
			PatientDTOComparator dateComparator = new PatientDTOComparator();
			dateComparator.setOrder(order);
			patientsDTO = turnPatientsToDTO(patients);
			patientsDTO.sort(dateComparator);
			System.out.println("sortirano po datumu po orderu : " + dateComparator.getOrder());
		}
		
		else if(searchParam.equals("no-search")) {
			patients = patientService.findAllSorted(param, order);
			patientsDTO = turnPatientsToDTO(patients);
		}
		System.out.println("Sorting by: " + param + "\nOrder: " + (order) + "\nCurrent search res for: " + searchParam);
		return new ResponseEntity<String>(g.toJson(patientsDTO), HttpStatus.OK);
	}
	
	public List<PatientDTO> turnPatientsToDTO(List<Patient> patients){
		List<PatientDTO> patientsDTO = new ArrayList<>();
		for (Patient p : patients) {
			LocalDateTime lastVisit = visitService.lastVisitByPatientIdEquals(p.getId());
			if(lastVisit!=null)
				patientsDTO.add(new PatientDTO(p, lastVisit.toLocalDate()));
			else
				patientsDTO.add(new PatientDTO(p, null));
		}
		return patientsDTO;
	}
}
