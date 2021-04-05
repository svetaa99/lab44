package backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
	
	public List<Pharmacy> findAllByNameContainingIgnoreCase(String name);

	public List<Pharmacy> findAllByRating(double rating);
	
}
