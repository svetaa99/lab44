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

import backend.dto.PharmacistDTO;
import backend.dto.PharmacyDTO;
import backend.models.Pharmacist;
import backend.models.Pharmacy;
import backend.services.IPharmacistService;
import backend.services.IPharmacyService;

@RestController
@RequestMapping(value = "pharmacist")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacistController {
	
	@Autowired
	private IPharmacyService pharmacyService;
	
	@Autowired
	private IPharmacistService pharmacistService;
	
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
		List<Pharmacist> pharmacists = pharmacistService.findAllByPharmacy(id);
		return new ResponseEntity<List<PharmacistDTO>>(createPharmacistDTOList(pharmacists), HttpStatus.OK);
	}
	
	@GetMapping("/sort/rating/{type}/{id}")
	private ResponseEntity<List<PharmacistDTO>> getSortedByRating(@PathVariable String type, @PathVariable Long id) {

		List<Pharmacist> pharmacists = (List<Pharmacist>) pharmacistService.sortByRating(type, id);
		List<PharmacistDTO> pharmacistsDTO = createPharmacistDTOList(pharmacists);
		
		return new ResponseEntity<List<PharmacistDTO>>(pharmacistsDTO, HttpStatus.OK);
	}
	
}
