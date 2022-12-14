package com.musala.drone_delivery.repository;

import com.musala.drone_delivery.model.jpa.LoadDrone;
import com.musala.drone_delivery.model.jpa.MedicationLoad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/24/22
 */
@Repository
public interface MedicationLoadRepository extends JpaRepository<MedicationLoad, Long> {
    List<MedicationLoad> findAllByLoadDrone(LoadDrone loadDrone);
}
