package backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.Pharmacist;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
	
	public List<Pharmacist> findAllByPharmacyIdEquals(Long pharmacistId);

}
