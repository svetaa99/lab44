package backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.DoctorTerms;

public interface DoctorTermsRepository extends JpaRepository<DoctorTerms, Long>{

	public List<DoctorTerms> findByDoctorIdEquals(Long id);
	
}
