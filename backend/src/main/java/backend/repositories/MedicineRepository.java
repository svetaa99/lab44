package backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

}
