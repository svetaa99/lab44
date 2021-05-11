package backend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.dto.DermatologistDTO;
import backend.dto.PharmacistDTO;
import backend.models.Dermatologist;
import backend.services.IDermatologistService;
import backend.services.IPharmacyService;

@RestController
@RequestMapping(value = "dermatologists")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DermatologistController {
	
	@Autowired
	private IDermatologistService dermaService;
	
	@Autowired
	private IPharmacyService pharmacyService;
	
	private List<DermatologistDTO> createDTOList(List<Dermatologist> derms) {
		List<DermatologistDTO> dDTOs = new ArrayList<DermatologistDTO>();
		
		for (Dermatologist dermatologist : derms) {
			dDTOs.add(new DermatologistDTO(dermatologist));
		}
		
		return dDTOs;
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasAnyRole('PATIENT', 'LAB_ADMIN', 'DERMATOLOGIST')")
	public ResponseEntity<List<DermatologistDTO>> findAll() {
		List<Dermatologist> derms = dermaService.findAll();
		
		return new ResponseEntity<List<DermatologistDTO>>(createDTOList(derms), HttpStatus.OK);
	}
	
	@PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DermatologistDTO>> searchDermatologists(@RequestBody DermatologistDTO obj) {
		String name = obj.getName();
		String surname = obj.getSurname();
		
		List<Dermatologist> derms = dermaService.findAllByNameOrSurname(name, surname);
		
		return new ResponseEntity<List<DermatologistDTO>>(createDTOList(derms), HttpStatus.OK);
	}
	
	@PostMapping(value = "/filter/{param}/{value}")
	public ResponseEntity<List<DermatologistDTO>> filterDermatologists(@RequestBody List<DermatologistDTO> searchList, @PathVariable("param") String param, @PathVariable("value") String value) {
		List<DermatologistDTO> retVal = null;
		
		if (param.equals("pharmacy")) {
			retVal = searchList
					.stream()
					.filter(d -> d.getPharmacies().contains(pharmacyService.findById(Long.parseLong(value))))
					.collect(Collectors.toList());
		}
		
		if (retVal == null) {
			return new ResponseEntity<List<DermatologistDTO>>(createDTOList(dermaService.findAll()), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<DermatologistDTO>>(retVal, HttpStatus.OK);
		}
	}
	
}
