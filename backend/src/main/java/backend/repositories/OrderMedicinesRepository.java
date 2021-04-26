package backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.OrderMedicines;

public interface OrderMedicinesRepository extends JpaRepository<OrderMedicines, Long> {

}
