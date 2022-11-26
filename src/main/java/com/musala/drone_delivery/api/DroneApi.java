package com.musala.drone_delivery.api;

import com.musala.drone_delivery.model.dto.BatteryLevelDto;
import com.musala.drone_delivery.model.dto.DroneDto;
import com.musala.drone_delivery.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/24/22
 */
@RestController
@RequestMapping("v1/api/drone")
public class DroneApi {

    private DroneService droneService;

    @Autowired
    public DroneApi(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping(path = "register")
    public DroneDto registerDrone(@RequestBody DroneDto droneDto){
        return droneService.registerDrone(droneDto);
    }

    @GetMapping(path = "available")
    public List<DroneDto> getAvailableDronesForLoading(){
        return droneService.getAvailableDronesForLoading();
    }

    @GetMapping(path = "battery/level/{serialNumber}")
    public BatteryLevelDto getDroneBatteryCapacity(@PathVariable String serialNumber){
        return droneService.getDroneBatteryLevel(serialNumber);
    }

}
