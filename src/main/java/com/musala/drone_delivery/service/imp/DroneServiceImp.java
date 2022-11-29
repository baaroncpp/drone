package com.musala.drone_delivery.service.imp;

import com.musala.drone_delivery.model.dto.*;
import com.musala.drone_delivery.model.enums.State;
import com.musala.drone_delivery.model.jpa.Drone;
import com.musala.drone_delivery.model.jpa.LoadDrone;
import com.musala.drone_delivery.model.jpa.Medication;
import com.musala.drone_delivery.model.jpa.MedicationLoad;
import com.musala.drone_delivery.repository.DroneRepository;
import com.musala.drone_delivery.repository.LoadDroneRepository;
import com.musala.drone_delivery.repository.MedicationLoadRepository;
import com.musala.drone_delivery.repository.MedicationRepository;
import com.musala.drone_delivery.service.DroneService;
import com.musala.drone_delivery.utils.DtoService;
import com.musala.drone_delivery.utils.Utilities;
import com.musala.drone_delivery.utils.Validate;
import com.musala.drone_delivery.utils.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

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
        droneDto.setState(State.IDLE);

        Optional<Drone> existingDrone = droneRepository.findBySerialNumber(droneDto.getSerialNumber());
        Validate.isTrue(!existingDrone.isPresent(), String.format("Drone with serial number %s is already registered", droneDto.getSerialNumber()));

        Drone registeredDrone = droneRepository.save(dtoService.dtoToDrone(droneDto));

        return dtoService.droneToDto(registeredDrone);
    }

    @Transactional
    @Override
    public LoadDroneDto loadDrone(LoadDroneDto loadDroneDto) {

        loadDroneDto.validate();
        Optional<Drone> existingDrone = droneRepository.findBySerialNumber(loadDroneDto.getDrone().getSerialNumber());
        Validate.isPresent(existingDrone, String.format("The Drone with serial number %s does not exist", loadDroneDto.getDrone().getSerialNumber()));

        var loadDrone = dtoService.dtoToLoadDrone(loadDroneDto);

        double totalMedicationWeight = loadDroneDto.getMedicationLoads().stream()
                        .mapToDouble(MedicationLoadDto::getMedicationTotalWeight)
                                .sum();

        loadDrone.setDroneLoadTotalWeight(totalMedicationWeight);
        Utilities.checkIfLoadDroneWeightCanBeDelivered(loadDrone);

        LoadDrone savedLoadDrone = loadDroneRepository.save(loadDrone);

        saveMedicationLoad(loadDroneDto.getMedicationLoads(), savedLoadDrone);
        List<MedicationLoad> savedMedicationLoadList = medicationLoadRepository.findAllByLoadDrone(savedLoadDrone);

        var resultMedicationLoadDtoList = savedMedicationLoadList.stream()
                .map(dto -> dtoService.medicationLoadToDto(dto))
                .collect(Collectors.toList());

        var resultingLoadDroneDto = dtoService.loadDroneToDto(savedLoadDrone);
        resultingLoadDroneDto.setMedicationLoads(resultMedicationLoadDtoList);

        return resultingLoadDroneDto;
    }

    @Override
    public List<MedicationDto> getDroneLoadedItems(Long droneLoadId) {

        Optional<LoadDrone> existingLoadDrone = loadDroneRepository.findById(droneLoadId);
        Validate.isPresent(existingLoadDrone, "Load with ID: %s does not exist");

        List<MedicationLoad> existingMedicationLoads = medicationLoadRepository.findAllByLoadDrone(existingLoadDrone.get());
        Validate.notNull(existingMedicationLoads, String.format("No medication found for drone Load with ID: %s", droneLoadId));


        return existingMedicationLoads.stream()
                .map(dto -> dtoService.medicationToDto(dto.getMedication()))
                .collect(Collectors.toList());
    }

    @Override
    public List<LoadDroneDto> getDroneLoads(String droneSerialNumber) {

        Optional<Drone> existingDrone = droneRepository.findBySerialNumber(droneSerialNumber);
        Validate.isPresent(existingDrone,  String.format("Drone with serial number %s does not exist", droneSerialNumber));

        List<LoadDrone> existingLoadDrones = loadDroneRepository.findAllByDrone(existingDrone.get());
        Validate.notNull(existingLoadDrones, String.format("No existing Loads for drone with serial number %s", droneSerialNumber));

        return existingLoadDrones.stream()
                .map(dto -> dtoService.loadDroneToDto(dto))
                .collect(Collectors.toList());
    }

    @Override
    public List<DroneDto> getAvailableDronesForLoading() {

        List<DroneDto> droneDtoList  = droneRepository.findAll().stream()
                                .filter(drone -> {
                                    return drone.getState().equals(State.IDLE);
                                })
                                .map(drone -> dtoService.droneToDto(drone))
                                .collect(Collectors.toList());

        Validate.isTrue(!droneDtoList.isEmpty(), "No available drones");

        return droneDtoList;
    }

    @Override
    public BatteryLevelDto getDroneBatteryLevel(String droneSerialNumber) {
        Optional<Drone> existingDrone = droneRepository.findBySerialNumber(droneSerialNumber);
        Validate.isTrue(existingDrone.isPresent(), String.format("Drone with serial number %s does not exist", droneSerialNumber));

        return BatteryLevelDto.builder()
                .batteryLevel(existingDrone.get().getBatteryCapacity())
                .build();
    }

    private void saveMedicationLoad(List<MedicationLoadDto> medicationLoadDtoList, LoadDrone loadDrone){

        medicationLoadDtoList.stream()
                .forEach(medicationLoadDto -> {

                    medicationLoadDto.validate();

                    var medication = medicationRepository.findByCode(medicationLoadDto.getMedication().getCode())
                            .orElseThrow(
                                    () -> new BadRequestException("Medication code %s does not exist", medicationLoadDto.getMedication().getCode())
                            );

                    var medicationLoad = dtoService.dtoToMedicationLoad(medicationLoadDto);
                    medicationLoad.setCreatedOn(new Date());
                    medicationLoad.setMedication(medication);
                    medicationLoad.setLoadDrone(loadDrone);

                    medicationLoadRepository.save(medicationLoad);
                });

    }
}
