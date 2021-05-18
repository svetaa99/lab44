package backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.models.DemandMedicine;
import backend.models.LabAdmin;
import backend.services.IDemandMedicineService;
import backend.services.ILabAdminService;

@RestController
@RequestMapping(value = "demandmedicines")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DemandMedicineController {
	
	@Autowired
	private IDemandMedicineService dmService;
	
	@Autowired
	private ILabAdminService laService;
	
	

}
