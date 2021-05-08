package backend.controllers;

import java.util.ArrayList;
import java.util.List;

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

import backend.dto.PharmacistDTO;
import backend.dto.PharmacyDTO;
import backend.models.Pharmacist;
import backend.models.Pharmacy;
import backend.models.Role;
import backend.models.User;
import backend.services.IPharmacistService;
import backend.services.IPharmacyService;
import backend.services.impl.UserService;

@RestController
@RequestMapping(value = "pharmacist")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacistController {
	
	@Autowired
	private IPharmacistService pharmacistService;
	
	@Autowired
	private UserService userService;
	
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
	public ResponseEntity<List<PharmacistDTO>> getSortedByRating(@PathVariable String type, @PathVariable Long id) {
		List<Pharmacist> pharmacists = (List<Pharmacist>) pharmacistService.sortByRating(type, id);
		List<PharmacistDTO> pharmacistsDTO = createPharmacistDTOList(pharmacists);
		
		return new ResponseEntity<List<PharmacistDTO>>(pharmacistsDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/create-new", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('LAB_ADMIN')")
	public ResponseEntity<PharmacistDTO> createNewPharmacist(@RequestBody PharmacistDTO obj) {
		Pharmacist p = new Pharmacist();
		
		List<User> allP = userService.findAll();
		int size = allP.size();
		
		p.setId(size + 1l);
		p.setName(obj.getName());
		p.setSurname(obj.getSurname());
		p.setEmail(obj.getEmail());
		p.setPassword("changem3");   // generic password that the pharmacist will change when first loggs in
		p.setAddress(obj.getAddress());
		p.setPhoneNum(obj.getPhoneNum());
		p.setPharmacy(obj.getPharmacy());
		p.setRating(0);
		
		Role role = new Role();
		role.setName("ROLE_PHARMACIST");
		role.setId(3l);
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		
		p.setRoles(roles);
		
		pharmacistService.save(p);
		
		return new ResponseEntity<PharmacistDTO>(new PharmacistDTO(p), HttpStatus.CREATED);
	}
	
}
