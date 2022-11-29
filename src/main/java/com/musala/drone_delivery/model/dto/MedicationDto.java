package com.musala.drone_delivery.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.musala.drone_delivery.utils.Validate;
import lombok.Data;

import java.util.Date;
import java.util.regex.Pattern;

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

    public void validate(){
        Validate.notNull(name, "Medication name cannot be NULL");
        Validate.notNull(name.length() < 1, "Medication name cannot be blank");
        Validate.isTrue(Pattern.matches("^[a-zA-Z0-9_-]+$",name), "Medication name contains unwanted characters");
        Validate.isTrue(weight < 1, "Medication weight cannot be Zero or less");
        Validate.notNull(weight, "Medication weight cannot be NULL");
        Validate.notNull(code, "Medication code cannot be NULL");
        Validate.isTrue(Pattern.matches("^[A-Z0-9_]+$",code), "Medication code contains unwanted characters");
    }
}
