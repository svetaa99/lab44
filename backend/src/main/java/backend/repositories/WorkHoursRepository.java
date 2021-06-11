package backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.WorkHours;

public interface WorkHoursRepository extends JpaRepository<WorkHours, Long>{

	List<WorkHours> findByDoctorIdAndPharmacyId(Long doctorId, Long pharmacyId);

	List<WorkHours> findByDoctorIdEquals(Long doctorId);
}
