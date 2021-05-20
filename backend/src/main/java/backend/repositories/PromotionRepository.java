package backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

}
