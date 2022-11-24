package com.musala.drone_delivery.model.enums;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/24/22
 */
public enum Model {
    LIGHT_WEIGHT("Lightweight"),
    MIDDLE_WEIGHT("Middleweight"),
    CRUISER_WEIGHT("Cruiserweight"),
    HEAVY_WEIGHT("Heavyweight");

    String description;

    Model(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
