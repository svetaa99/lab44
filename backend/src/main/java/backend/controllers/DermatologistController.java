package backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.dto.DermatologistDTO;
import backend.models.Dermatologist;
import backend.services.IDermatologistService;

@RestController
@RequestMapping(value = "dermatologists")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DermatologistController {
	
	@Autowired
	private IDermatologistService dermaService;
	
	@GetMapping("/all")
	public ResponseEntity<List<DermatologistDTO>> findAll() {
		List<Dermatologist> derms = dermaService.findAll();
		List<DermatologistDTO> dDTOs = new ArrayList<DermatologistDTO>();
		
		for (Dermatologist dermatologist : derms) {
			dDTOs.add(new DermatologistDTO(dermatologist));
		}
		
		return new ResponseEntity<List<DermatologistDTO>>(dDTOs, HttpStatus.OK);
	}
}
