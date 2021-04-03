package backend.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import backend.dto.PharmacyDTO;
import backend.models.Pharmacy;
import backend.services.PharmacyService;

@RestController
@RequestMapping(value = "pharmacies")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacyController {

	@Autowired
	private PharmacyService pharmacyService;
	private static Gson g = new Gson();
	
	private List<PharmacyDTO> createPharmacyDTOList(List<Pharmacy> pharmacies) {
		List<PharmacyDTO> pharmaciesDTO = new ArrayList<PharmacyDTO>();
		
		for (Pharmacy pharmacy : pharmacies) {
			PharmacyDTO pharmacyDTO = new PharmacyDTO(pharmacy);
			pharmaciesDTO.add(pharmacyDTO);
		}
		
		return pharmaciesDTO;
	}
	
	@GetMapping("/all")
	private ResponseEntity<List<PharmacyDTO>> getAllPharmacies() {
		List<Pharmacy> pharmacies = pharmacyService.findAll();
		List<PharmacyDTO> pharmaciesDTO = createPharmacyDTOList(pharmacies);
		
		return new ResponseEntity<List<PharmacyDTO>>(pharmaciesDTO, HttpStatus.OK);
	}
	
	@GetMapping("/search/{name}")
	private ResponseEntity<List<PharmacyDTO>> findAllByName(@PathVariable String name) {
		List<Pharmacy> pharmacies = (List<Pharmacy>) pharmacyService.findAllByName(name);
		List<PharmacyDTO> pharmaciesDTO = createPharmacyDTOList(pharmacies);
		
		return new ResponseEntity<List<PharmacyDTO>>(pharmaciesDTO, HttpStatus.OK);
	}
	
}
