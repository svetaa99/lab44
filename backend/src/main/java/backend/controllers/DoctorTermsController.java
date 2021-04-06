package backend.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import backend.models.DoctorTerms;
import backend.models.SearchDateTime;
import backend.services.impl.DoctorTermsService;

@RestController
@RequestMapping(value = "doctorterms")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DoctorTermsController {

	@Autowired
	private DoctorTermsService doctorTermsService;
	private static Gson g = new Gson();
	
	@GetMapping("/definedterms")
	public ResponseEntity<String> getDefinedTerms(){
		System.out.println("Returning predefined terms for doctor in current session...");
		//extract real doctors id -- now hardcoded on doctorId = 1
		//extract real pharmacy id -- now hardcoded on pharmacyId = 1, might send this as method param?
		Long doctorId = (long) 1;
		//Long pharmacyId = (long) 1;
		
		List<DoctorTerms> retVal = doctorTermsService.findByDoctorIdEquals(doctorId);
		
		return new ResponseEntity<String>(g.toJson(retVal), HttpStatus.OK);
	}
	
	@PostMapping(value = "/createnew", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveNewTerm(@RequestBody DoctorTerms newTerm){
		System.out.println("We got : " + newTerm + "\n\n from client...");
		
		if(checkIfTakenTerm(newTerm)) {
			doctorTermsService.save(newTerm);
			System.out.println("Object saved to db...");
		}
		else {
			System.out.println("Taken term...");
			return new ResponseEntity<String>("Taken term", HttpStatus.OK);
		}
			
		return new ResponseEntity<String>(g.toJson(doctorTermsService.findByDoctorIdEquals(newTerm.getDoctorId())), HttpStatus.OK);
	}
	
	@PostMapping(value = "/search-date-time", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<String> searchDateTime(@RequestBody SearchDateTime newDateTime){
		//get doctors id from session for now hardcode to 1
		Long doctorId = (long) 1;
		List<DoctorTerms> retVal = doctorTermsService.findByDoctorIdEquals(doctorId);
		
		System.out.println("Termina ukupno: " + retVal.size());
		System.out.println("Datum dobijen = " + newDateTime.getSearchDate());
		
		if(newDateTime.getSearchDate() != null)
			retVal = retVal.stream().filter(t -> t.getStart().toLocalDate().equals(newDateTime.getSearchDate())).collect(Collectors.toList());
		
		if(newDateTime.getSearchTime() != null)
			retVal = retVal.stream().filter(t -> t.getStart().getHour() == (newDateTime.getSearchTime().getHour())).collect(Collectors.toList());
		
		System.out.println("Pretragom nadjeno: " + retVal.size());
		
		return new ResponseEntity<String>(g.toJson(retVal), HttpStatus.OK);
	}
	
	private  boolean checkIfTakenTerm(DoctorTerms newTerm) {
		List<DoctorTerms> doctorsTakenTerms = doctorTermsService.findByDoctorIdEquals(newTerm.getDoctorId());
		LocalDateTime startTime = newTerm.getStart();
		LocalDateTime finishTime = newTerm.getFinish();
		for (DoctorTerms doctorTerms : doctorsTakenTerms) {
			if(startTime.isAfter(doctorTerms.getStart()) && startTime.isBefore(doctorTerms.getFinish())) 
				return false;
			else if(finishTime.isAfter(doctorTerms.getStart()) && finishTime.isBefore(doctorTerms.getFinish())) 
				return false;
			else if(startTime.isBefore(doctorTerms.getStart()) && finishTime.isAfter(doctorTerms.getFinish()))
				return false;
		}
		//check if working hours match the term 
		return true;
	}
}
