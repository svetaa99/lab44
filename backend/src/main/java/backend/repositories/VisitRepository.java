package backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long>{

	List<Visit> findByPatientIdEquals(Long patientId);
	
	List<Visit> findByDoctorIdEquals(Long doctorId);
	
	List<Visit> findByIdEquals(Long id);
}
