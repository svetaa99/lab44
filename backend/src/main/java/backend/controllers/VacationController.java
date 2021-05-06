package backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.enums.VacationStatus;
import backend.models.Doctor;
import backend.models.User;
import backend.models.Vacation;
import backend.services.impl.UserService;
import backend.services.impl.VacationService;

@RestController
@RequestMapping(value = "vacation")
public class VacationController {

	@Autowired
	private VacationService vacationService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST', 'LAB_ADMIN')")
	public ResponseEntity<Vacation> saveVacationRequest(@RequestBody Vacation vacation){
		String token = SecurityContextHolder.getContext().getAuthentication().getName();
		User u = userService.findUserByEmail(token);
		
		vacation.setDoctor((Doctor) u);
		vacation.setStatus(VacationStatus.ON_HOLD);
		System.out.println("TYPE : " + vacation.getType());
		
		Vacation saved = vacationService.save(vacation);
		
		return new ResponseEntity<Vacation>(saved, HttpStatus.OK);
	}
	
	@GetMapping("/current")
	@PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST')")
	public ResponseEntity<String> getCurrent(){
		//checks if employee has unresolved vacation requests VacationStatus = ON_HOLD
		//returns YES or NO
		String token = SecurityContextHolder.getContext().getAuthentication().getName();
		User u = userService.findUserByEmail(token);
		Long doctorId = u.getId();
		
		List<Vacation> list = vacationService.findByDoctorIdAndStatus(doctorId, VacationStatus.ON_HOLD);
		
		if(list.size() > 0)
			return new ResponseEntity<String>("YES", HttpStatus.OK);
		
		return new ResponseEntity<String>("NO", HttpStatus.OK);
	}
}
