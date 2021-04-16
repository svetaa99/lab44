package backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.Reservation;
import backend.repositories.ReservationRepository;
import backend.services.IReservationService;

@Service
public class ReservationService implements IReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	@Override
	public Reservation findById(Long id) {
		return reservationRepository.findById(id).orElseGet(null);
	}

	@Override
	public Reservation save(Reservation obj) {
		return reservationRepository.save(obj);
	}

	@Override
	public void delete(Reservation obj) {
		reservationRepository.delete(obj);
	}

}
