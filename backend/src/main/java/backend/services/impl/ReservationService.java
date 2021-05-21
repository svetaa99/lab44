package backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.enums.Status;
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
		return reservationRepository.findById(id).orElse(null);
	}

	@Override
	public Reservation save(Reservation obj) {
		return reservationRepository.save(obj);
	}

	@Override
	public void delete(Reservation obj) {
		reservationRepository.delete(obj);
	}
	
	@Override
	public List<Reservation> findMy(Long patientId) {
		return reservationRepository.findAllByPatientId(patientId);
	}
	
	@Override
	public List<Reservation> findReserved() {
		return reservationRepository.findAllByStatus(Status.RESERVED);
	}

	@Override
	public List<Reservation> findByPatientAndMedicineReserved(Long patientId, Long medicineId) {
		return reservationRepository.findAllByPatientIdAndMedicineIdAndStatus(patientId, medicineId, Status.FINISHED);
	}

	@Override
	public List<Reservation> findByPatientAndPharmacyReserved(Long pharmacyId, Long patientId) {
		return reservationRepository.findAllByPatientIdAndPharmacyAndStatus(patientId, pharmacyId, Status.FINISHED);
	}
	
}
