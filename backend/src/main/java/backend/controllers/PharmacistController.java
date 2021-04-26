package backend.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.dto.PharmacistDTO;
import backend.models.Pharmacist;
import backend.models.Pharmacy;
import backend.services.IPharmacistService;
import backend.services.IPharmacyService;
import backend.services.impl.PharmacistService;

@RestController
@RequestMapping(value = "pharmacist")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacistController {

	@Autowired
	private PharmacistService pharmacistService;
	
	@Autowired
	private IPharmacyService pharmacyService;
	
	private List<PharmacistDTO> createPharmacistDTOList(List<Pharmacist> pharmacists) {
		
		List<PharmacistDTO> pharmacistsDTO = new ArrayList<PharmacistDTO>();
		
		for (Pharmacist pharmacist : pharmacists) {
			PharmacistDTO pharmacistDTO = new PharmacistDTO(pharmacist);
			pharmacistsDTO.add(pharmacistDTO);
		}
		
		return pharmacistsDTO;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<List<PharmacistDTO>> getPharmacistsFromPharmacy(@PathVariable Long id) {
		Pharmacy pharmacy = pharmacyService.findById(id);
		List<Pharmacist> pharmasists = pharmacy.getPharmacists();
		System.out.println(pharmasists);
		return new ResponseEntity<List<PharmacistDTO>>(createPharmacistDTOList(pharmasists), HttpStatus.OK);
	}
	
}
