package backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long>{

}
