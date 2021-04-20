package backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
