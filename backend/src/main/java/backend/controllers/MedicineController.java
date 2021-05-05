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

import backend.dto.MedicineDTO;
import backend.models.Medicine;
import backend.services.IMedicineService;

@RestController
@RequestMapping(value = "medicines")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicineController {
	
	@Autowired
	private IMedicineService medicineService;
	
	private List<MedicineDTO> createMedicineDTOList(List<Medicine> medicines) {
		List<MedicineDTO> medicinesDTO = new ArrayList<MedicineDTO>();
		
		for (Medicine medicine : medicines) {
			MedicineDTO medicineDTO = new MedicineDTO(medicine);
			medicinesDTO.add(medicineDTO);
		}
		
		return medicinesDTO;
	}

	@GetMapping("/all")
	public ResponseEntity<List<MedicineDTO>> getAllMedicines() {
		List<Medicine> medicines = medicineService.findAll();
		List<MedicineDTO> medicinesDTO = createMedicineDTOList(medicines);
		
		return new ResponseEntity<List<MedicineDTO>>(medicinesDTO, HttpStatus.OK);
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<List<MedicineDTO>> getAllByName(@PathVariable String name) {
		List<Medicine> medicines = (List<Medicine>) medicineService.findAllByName(name);
		List<MedicineDTO> medicinesDTO = createMedicineDTOList(medicines);
		
		return new ResponseEntity<List<MedicineDTO>>(medicinesDTO, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MedicineDTO> getById(@PathVariable Long id) {
		Medicine medicine = medicineService.findById(id);
		if (medicine.equals(null)) {
			return new ResponseEntity<MedicineDTO>(HttpStatus.NOT_FOUND);
		}
		
		MedicineDTO medicineDTO = new MedicineDTO(medicine);
		
		return new ResponseEntity<MedicineDTO>(medicineDTO, HttpStatus.OK);
	}
	
}
