package backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.Ratings;

public interface RatingRepository extends JpaRepository<Ratings, Long> {

	List<Ratings> findByPatientIdEquals(Long patientId);
	
	List<Ratings> findByObjIdEquals(Long objId);
	
	List<Ratings> findByTypeEquals(int type);
	
}
