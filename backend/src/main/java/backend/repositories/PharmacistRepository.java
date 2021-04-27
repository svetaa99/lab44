package backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.Pharmacist;
import backend.models.Pharmacy;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
	
	public List<Pharmacist> findAllByPharmacyIdEquals(Long pharmacistId);
	
	public List<Pharmacist> findAllByPharmacyIdOrderByRatingAsc(Long pharmacistId);
	
	public List<Pharmacist> findAllByPharmacyIdOrderByRatingDesc(Long pharmacistId);

}
