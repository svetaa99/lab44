package backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.dto.ReservationDTO;
import backend.models.Medicine;
import backend.models.Patient;
import backend.models.Pharmacy;
import backend.models.Reservation;
import backend.services.IMedicineService;
import backend.services.IPatientService;
import backend.services.IPharmacyService;
import backend.services.IReservationService;

@RestController
@RequestMapping(value = "reservations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservationController {
	
	@Autowired
	private IReservationService reservationService;
	
	@Autowired
	private IPatientService patientService;
	
	@Autowired
	private IPharmacyService pharmacyService;
	
	@Autowired
	private IMedicineService medicineService;
	
	
	
	private List<ReservationDTO> createReservationDTOList(List<Reservation> reservations) {
		List<ReservationDTO> rDTOs = new ArrayList<ReservationDTO>();
		
		for (Reservation reservation : reservations) {
			rDTOs.add(new ReservationDTO(reservation));
		}
		
		return rDTOs;
	}
	
	@GetMapping()
	public ResponseEntity<List<ReservationDTO>> getAll() {
		List<Reservation> reservations = reservationService.findAll();
		
		return new ResponseEntity<List<ReservationDTO>>(createReservationDTOList(reservations), HttpStatus.OK);
	}
	
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReservationDTO> createReservation(@RequestBody Reservation reservation) {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAa" + reservation);
		Patient patient = reservation.getPatient();
		if (patientService.findById(patient.getId()).equals(null)) {
			return new ResponseEntity<ReservationDTO>(HttpStatus.NOT_FOUND);
		}
		
		Pharmacy pharmacy = reservation.getPharmacy();
		if (pharmacyService.findById(pharmacy.getId()).equals(null)) {
			return new ResponseEntity<ReservationDTO>(HttpStatus.NOT_FOUND);
		}
		
		Medicine medicine = reservation.getMedicine();
		if (medicineService.findById(medicine.getId()).equals(null)) {
			return new ResponseEntity<ReservationDTO>(HttpStatus.NOT_FOUND);
		}
		
		long date = reservation.getDate();
		if (date < 0) {
			return new ResponseEntity<ReservationDTO>(HttpStatus.BAD_REQUEST);	// status code 400
		}
		
		int quantity = reservation.getQuantity();
		if (quantity < 1) {
			return new ResponseEntity<ReservationDTO>(HttpStatus.BAD_REQUEST);	// status code 400
		}
		
		double totalPrice = reservation.getTotalPrice();
		if (totalPrice < 0) {
			return new ResponseEntity<ReservationDTO>(HttpStatus.BAD_REQUEST);	// status code 400
		}
		
		reservation = reservationService.save(reservation);
		ReservationDTO rDTO = new ReservationDTO(reservation);
		
		return new ResponseEntity<ReservationDTO>(rDTO, HttpStatus.OK);
	}
}
