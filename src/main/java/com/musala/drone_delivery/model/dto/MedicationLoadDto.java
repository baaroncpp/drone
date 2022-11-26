package com.musala.drone_delivery.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.musala.drone_delivery.utils.Validate;
import lombok.Data;

import java.util.Date;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/24/22
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MedicationLoadDto {
    private Long id;
    private Date createdOn;
    private LoadDroneDto loadDrone;
    private MedicationDto medication;
    private int quantity;
    private double medicationTotalWeight;

    public void validate(){
        Validate.isTrue(quantity > 0, "Quantity cannot be zero or less");
        Validate.notNull(medication.getCode(), "The medication cannot be NULL");
    }
}
