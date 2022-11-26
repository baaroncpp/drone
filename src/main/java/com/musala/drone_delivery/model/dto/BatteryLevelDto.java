package com.musala.drone_delivery.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/26/22
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class BatteryLevelDto {
    private double batteryLevel;
}
