package backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import backend.dto.PharmacyDTO;
import backend.models.Pharmacy;
import backend.services.PharmacyService;

@RestController
@RequestMapping(value = "pharmacies")
public class PharmacyController {

	@Autowired
	private PharmacyService labService;
	private static Gson g = new Gson();
	
	@GetMapping
	private ResponseEntity<String> getAllLabs() {
		List<Pharmacy> pharmacies = labService.findAll();
		
		List<PharmacyDTO> pharmaciesDTO = new ArrayList<PharmacyDTO>();
		
		for (Pharmacy pharmacy : pharmacies) {
			PharmacyDTO pharmacyDTO = new PharmacyDTO(pharmacy);
			pharmaciesDTO.add(pharmacyDTO);
		}
		
		return new ResponseEntity<String>(g.toJson(pharmaciesDTO), HttpStatus.OK);
	}
	
}
