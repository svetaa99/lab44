package backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.dto.ReservationDTO;
import backend.models.Reservation;
import backend.services.IReservationService;

@RestController
@RequestMapping(value = "reservations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservationController {
	
	@Autowired
	private IReservationService reservationService;
	
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
}
