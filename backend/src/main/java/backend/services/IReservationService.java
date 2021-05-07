package backend.services;

import java.util.List;

import backend.models.Reservation;

public interface IReservationService extends IService<Reservation> {

	List<Reservation> findMy(Long patientId);

}
