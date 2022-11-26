package com.musala.drone_delivery.service;

import antlr.collections.impl.LList;
import com.musala.drone_delivery.model.dto.BatteryLevelDto;
import com.musala.drone_delivery.model.dto.DroneDto;
import com.musala.drone_delivery.model.dto.LoadDroneDto;
import com.musala.drone_delivery.model.dto.MedicationDto;

import java.util.List;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/24/22
 */
public interface DroneService {
    DroneDto registerDrone(DroneDto droneDto);
    LoadDroneDto loadDrone(LoadDroneDto loadDroneDto);
    List<MedicationDto> getDroneLoadedItems(Long droneLoad);
    List<LoadDroneDto> getDroneLoads(String droneSerialNumber);
    List<DroneDto> getAvailableDronesForLoading();
    BatteryLevelDto getDroneBatteryLevel(String droneSerialNumber);
}
