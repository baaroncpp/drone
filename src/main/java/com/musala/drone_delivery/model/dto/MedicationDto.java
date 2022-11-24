package com.musala.drone_delivery.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class MedicationDto {
    private Long id;
    private Date createdOn;
    private String name;
    private double weight;
    private String code;
    private String imageUrl;
}
