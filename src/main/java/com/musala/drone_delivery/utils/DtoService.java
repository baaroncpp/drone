package com.musala.drone_delivery.utils;

import com.musala.drone_delivery.model.dto.DroneDto;
import com.musala.drone_delivery.model.dto.LoadDroneDto;
import com.musala.drone_delivery.model.dto.MedicationDto;
import com.musala.drone_delivery.model.dto.MedicationLoadDto;
import com.musala.drone_delivery.model.jpa.Drone;
import com.musala.drone_delivery.model.jpa.LoadDrone;
import com.musala.drone_delivery.model.jpa.Medication;
import com.musala.drone_delivery.model.jpa.MedicationLoad;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/24/22
 */
@Service
public class DtoService {

    private ModelMapper modelMapper;

    @Autowired
    public DtoService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DroneDto droneToDto(Drone drone){
        return modelMapper.map(drone, DroneDto.class);
    }

    public Drone dtoToDrone(DroneDto droneDto){
        return modelMapper.map(droneDto, Drone.class);
    }

    public MedicationDto medicationToDto(Medication medication){
        return modelMapper.map(medication, MedicationDto.class);
    }

    public Medication dtoToMedication(MedicationDto medicationDto){
        return modelMapper.map(medicationDto, Medication.class);
    }

    public MedicationLoadDto medicationLoadToDto(MedicationLoad medicationLoad){
        var medicationLoadDto = modelMapper.map(medicationLoad, MedicationLoadDto.class);
        medicationLoadDto.setMedication(medicationToDto(medicationLoad.getMedication()));
        return medicationLoadDto;
    }

    public MedicationLoad dtoToMedicationLoad(MedicationLoadDto medicationLoadDto){
        var medicationLoad = modelMapper.map(medicationLoadDto, MedicationLoad.class);
        medicationLoad.setMedication(dtoToMedication(medicationLoadDto.getMedication()));
        return medicationLoad;
    }

    public LoadDroneDto loadDroneToDto(LoadDrone loadDrone){
        var loadDroneDto = modelMapper.map(loadDrone, LoadDroneDto.class);
        loadDroneDto.setDrone(droneToDto(loadDrone.getDrone()));

        /*if(!loadDrone.getMedicationLoads().isEmpty()) {
            loadDroneDto.setMedicationLoads(loadDrone.getMedicationLoads().stream().map(
                    medicationLoad -> medicationLoadToDto(medicationLoad)
            ).collect(Collectors.toList()));
        }*/
        return loadDroneDto;
    }

    public LoadDrone dtoToLoadDrone(LoadDroneDto loadDroneDto){
        var loadDrone = modelMapper.map(loadDroneDto, LoadDrone.class);
        loadDrone.setDrone(dtoToDrone(loadDroneDto.getDrone()));

        if(!loadDroneDto.getMedicationLoads().isEmpty()) {
            loadDrone.setMedicationLoads(loadDroneDto.getMedicationLoads().stream().map(
                    medicationLoadDto -> dtoToMedicationLoad(medicationLoadDto)
            ).collect(Collectors.toList()));
        }
        return loadDrone;
    }

}
