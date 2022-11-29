package com.musala.drone_delivery.config;

import com.musala.drone_delivery.model.jpa.BatteryHistory;
import com.musala.drone_delivery.repository.BatteryHistoryRepository;
import com.musala.drone_delivery.repository.DroneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/29/22
 */
@Slf4j
@Configuration
@EnableScheduling
public class BatteryLevelEventConfig {
    /*
     * This schedule is based on the assumption that each drone updates battery capacity
     * */

    private DroneRepository droneRepository;
    private BatteryHistoryRepository batteryHistoryRepository;

    @Autowired
    public BatteryLevelEventConfig(DroneRepository droneRepository,
                             BatteryHistoryRepository batteryHistoryRepository) {
        this.droneRepository = droneRepository;
        this.batteryHistoryRepository = batteryHistoryRepository;
    }

    @Scheduled(fixedDelay = 4000)
    public void checkBatteryLevels(){

        System.out.println("battery log");

        droneRepository.findAll().stream()
                .forEach(
                        drone -> {
                            BatteryHistory batteryHistory = new BatteryHistory();
                            batteryHistory.setCreatedOn(new Date());
                            batteryHistory.setDrone(drone);
                            batteryHistory.setCurrentBatteryCapacity(drone.getBatteryCapacity());

                            batteryHistoryRepository.save(batteryHistory);

                            log.info(String.format("Current battery level for drone with serial number %s is %s", drone.getSerialNumber(), drone.getBatteryCapacity()));
                        }
                );
    }
}
