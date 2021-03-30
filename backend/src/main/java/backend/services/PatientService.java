package backend.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import backend.models.Patient;

public interface PatientService extends JpaRepository<Patient, Long>{

	@Query("select p from Patient p")
	public List<Patient> getAllPatients();
	
	public List<Patient> findAllByName(String name);
	
}
