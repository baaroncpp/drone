package com.musala.drone_delivery.repository;

import com.musala.drone_delivery.model.jpa.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/24/22
 */
@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    Optional<Medication> findByCode(String code);
}
