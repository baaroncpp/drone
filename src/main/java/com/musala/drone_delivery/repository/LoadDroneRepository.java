package com.musala.drone_delivery.repository;

import com.musala.drone_delivery.model.jpa.Drone;
import com.musala.drone_delivery.model.jpa.LoadDrone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/24/22
 */
@Repository
public interface LoadDroneRepository extends JpaRepository<LoadDrone, Long> {
    List<LoadDrone> findAllByDrone(Drone drone);
}
