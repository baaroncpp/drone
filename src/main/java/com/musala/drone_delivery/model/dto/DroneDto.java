package com.musala.drone_delivery.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.musala.drone_delivery.model.enums.Model;
import com.musala.drone_delivery.model.enums.State;
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

    }
}
