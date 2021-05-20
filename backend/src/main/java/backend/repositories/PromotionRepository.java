package backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
	
	List<Promotion> findByPharmacyIdEquals(Long pharmacyId);
}
