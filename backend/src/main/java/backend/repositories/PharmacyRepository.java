package backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import backend.models.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
	
	@Query("select p from Pharmacy p")
	public List<Pharmacy> getPharmacies();
}
