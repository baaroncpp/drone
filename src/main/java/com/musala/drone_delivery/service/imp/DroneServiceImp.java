package com.musala.drone_delivery.service.imp;

import com.musala.drone_delivery.model.dto.DroneDto;
import com.musala.drone_delivery.model.dto.LoadDroneDto;
import com.musala.drone_delivery.model.dto.MedicationDto;
import com.musala.drone_delivery.model.enums.State;
import com.musala.drone_delivery.model.jpa.Drone;
import com.musala.drone_delivery.repository.DroneRepository;
import com.musala.drone_delivery.repository.LoadDroneRepository;
import com.musala.drone_delivery.repository.MedicationLoadRepository;
import com.musala.drone_delivery.repository.MedicationRepository;
import com.musala.drone_delivery.service.DroneService;
import com.musala.drone_delivery.utils.DtoService;
import com.musala.drone_delivery.utils.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/24/22
 */
@Service
public class DroneServiceImp implements DroneService {

    private DroneRepository droneRepository;
    private LoadDroneRepository loadDroneRepository;
    private MedicationRepository medicationRepository;
    private MedicationLoadRepository medicationLoadRepository;
    private DtoService dtoService;

    @Autowired
    public DroneServiceImp(DroneRepository droneRepository,
                           LoadDroneRepository loadDroneRepository,
                           MedicationRepository medicationRepository,
                           MedicationLoadRepository medicationLoadRepository,
                           DtoService dtoService) {
        this.droneRepository = droneRepository;
        this.loadDroneRepository = loadDroneRepository;
        this.medicationRepository = medicationRepository;
        this.medicationLoadRepository = medicationLoadRepository;
        this.dtoService = dtoService;
    }

    @Override
    public DroneDto registerDrone(DroneDto droneDto) {

        droneDto.validate();

        Optional<Drone> existingDrone = droneRepository.findBySerialNumber(droneDto.getSerialNumber());
        Validate.isTrue(!existingDrone.isPresent(), String.format("Drone with serial number %s is already registered", droneDto.getSerialNumber()));

        Drone registeredDrone = droneRepository.save(dtoService.dtoToDrone(droneDto));

        return dtoService.droneToDto(registeredDrone);
    }

    @Override
    public LoadDroneDto loadDrone(LoadDroneDto loadDroneDto) {
        return null;
    }

    @Override
    public List<MedicationDto> getDroneLoadedItems(Long droneLoad) {
        return null;
    }

    @Override
    public List<LoadDroneDto> getDroneLoads(String droneSerialNumber) {
        return null;
    }

    @Override
    public List<DroneDto> getAvailableDronesForLoading() {

        Validate.isTrue(droneRepository.findByStateEqual(State.IDLE).isEmpty(), "No available drones for loading");
        return null;
    }

    @Override
    public Double getDroneBatteryLevel(String droneSerialNumber) {
        return null;
    }
}
