package backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.Dermatologist;

public interface DermatologistRepository extends JpaRepository<Dermatologist, Long> {

}
