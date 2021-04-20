package backend.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.dto.PharmacistDTO;
import backend.models.Pharmacist;
import backend.services.IPharmacistService;
import backend.services.impl.PharmacistService;

@RestController
@RequestMapping(value = "pharmacist")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacistController {

	@Autowired
	private PharmacistService pharmacistService;
	
	private List<PharmacistDTO> createPharmacistDTOList(List<Pharmacist> pharmacists) {
		List<PharmacistDTO> pharmacistsDTO = new ArrayList<PharmacistDTO>();
		
		for (Pharmacist pharmacist : pharmacists) {
			PharmacistDTO pharmacistDTO = new PharmacistDTO(pharmacist);
			pharmacistsDTO.add(pharmacistDTO);
		}
		
		return pharmacistsDTO;
	}
}
