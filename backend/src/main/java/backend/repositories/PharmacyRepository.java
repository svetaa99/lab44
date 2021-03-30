package backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

}
