package backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.DemandMedicine;

public interface DemandMedicineRepository extends JpaRepository<DemandMedicine, Long> {

}
