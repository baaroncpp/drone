package com.musala.drone_delivery.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.musala.drone_delivery.utils.Validate;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/24/22
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LoadDroneDto {
    private Long id;
    private Date createdOn;
    private DroneDto drone;
    private List<MedicationLoadDto> medicationLoads;
    private double DroneLoadTotalWeight;

    public void validate(){
        Validate.notNull(drone.getSerialNumber(), "The Drone serial number cannot be NULL");
        Validate.isTrue(!medicationLoads.isEmpty(), "No medications specified for this delivery");
        Validate.notNull(medicationLoads, "No medications list cannot be NULL");
    }
}
