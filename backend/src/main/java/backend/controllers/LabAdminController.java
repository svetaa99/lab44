package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.dto.LabAdminDTO;
import backend.models.LabAdmin;
import backend.services.ILabAdminService;

@RestController
@RequestMapping(value = "labadmins")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LabAdminController {

	@Autowired
	private ILabAdminService laService;
	
	@GetMapping("/registered-admin")
	public ResponseEntity<LabAdminDTO> getRegisteredAdmin() {
		String token = SecurityContextHolder.getContext().getAuthentication().getName();
		LabAdmin la = laService.findByEmail(token);
		
		return new ResponseEntity<LabAdminDTO>(new LabAdminDTO(la), HttpStatus.OK);
	}
}
