package com.musala.drone_delivery.repository;

import com.musala.drone_delivery.model.jpa.BatteryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/29/22
 */
public interface BatteryHistoryRepository extends JpaRepository<BatteryHistory, Long> {
}
