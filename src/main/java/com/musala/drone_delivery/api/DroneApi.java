package com.musala.drone_delivery.api;

import com.musala.drone_delivery.model.dto.DroneDto;
import com.musala.drone_delivery.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
