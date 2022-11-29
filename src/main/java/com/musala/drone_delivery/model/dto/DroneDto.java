package com.musala.drone_delivery.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.musala.drone_delivery.model.enums.Model;
import com.musala.drone_delivery.model.enums.State;
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
public class DroneDto {
    private Long id;
    private Date createdOn;
    private String serialNumber;
    private Model model;
    private double weightLimit;
    private double batteryCapacity;
    private State state;

    public void validate(){
        Validate.isTrue(weightLimit <= 500, "The Drone weight limit is 500 gr");
        Validate.isTrue(weightLimit > 0, "The Drone weight limit cannot be less than one");
        Validate.isTrue(serialNumber.length() <= 100, "SerialNumber cannot have more than 100 characters");
        Validate.notNull(model, "The drone model cannot be NULL");
        Validate.notNull(serialNumber, "The drone serial number cannot be NULL");
        Validate.notNull(weightLimit, "The drone weight limit cannot be NULL");
        Validate.isTrue((!model.equals(Model.Heavyweight) || !model.equals(Model.Cruiserweight) || !model.equals(Model.Middleweight) || !model.equals(Model.Lightweight)),"Invalid Drone model");
    }
}
