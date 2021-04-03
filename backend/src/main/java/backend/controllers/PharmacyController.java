package backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.dto.PharmacyDTO;
import backend.models.Medicine;
import backend.models.Pharmacy;
import backend.services.MedicineService;
import backend.services.PharmacyService;

@RestController
@RequestMapping(value = "pharmacies")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacyController {

	@Autowired
	private PharmacyService pharmacyService;
	
	@Autowired
	private MedicineService	medicineService;
	
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
	
	@GetMapping("/{id}")
	private ResponseEntity<PharmacyDTO> getById(@PathVariable Long id) {
		Pharmacy pharmacy = pharmacyService.findById(id);
		if (pharmacy.equals(null)) {
			return new ResponseEntity<PharmacyDTO>(HttpStatus.NOT_FOUND);
		}
		
		PharmacyDTO pharmacyDTO = new PharmacyDTO(pharmacy);
		
		return new ResponseEntity<PharmacyDTO>(pharmacyDTO, HttpStatus.OK);
	}
	
	@GetMapping("/search/{name}")
	private ResponseEntity<List<PharmacyDTO>> getAllByName(@PathVariable String name) {
		List<Pharmacy> pharmacies = (List<Pharmacy>) pharmacyService.findAllByName(name);
		List<PharmacyDTO> pharmaciesDTO = createPharmacyDTOList(pharmacies);
		
		return new ResponseEntity<List<PharmacyDTO>>(pharmaciesDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/{id}/add-medicine", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<PharmacyDTO> addMedicine(@PathVariable("id") Long pharmacyId, @RequestBody Medicine medicine) {
		Pharmacy pharmacy = pharmacyService.findById(pharmacyId);
		if (pharmacy.equals(null)) {
			return new ResponseEntity<PharmacyDTO>(HttpStatus.NOT_FOUND);
		}
		if (medicine.equals(null)) {
			return new ResponseEntity<PharmacyDTO>(HttpStatus.NOT_FOUND);
		}
		if (pharmacy.getMedicines().contains(medicine)) {
			return new ResponseEntity<PharmacyDTO>(HttpStatus.NOT_ACCEPTABLE);	// 406 status code
		}
		
		pharmacy.addMedicine(medicine);
		pharmacyService.save(pharmacy);
		PharmacyDTO pharmacyDTO = new PharmacyDTO(pharmacy);
		
		return new ResponseEntity<PharmacyDTO>(pharmacyDTO, HttpStatus.OK);
	}
	
}
