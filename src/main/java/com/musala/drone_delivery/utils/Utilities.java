package com.musala.drone_delivery.utils;

import com.musala.drone_delivery.model.enums.State;
import com.musala.drone_delivery.model.jpa.Drone;
import com.musala.drone_delivery.model.jpa.LoadDrone;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/24/22
 */
public class Utilities {

    private Utilities() { }

    private static final double BATTERY_PERCENTAGE_LIMIT = 25.0;
    private static final double WEIGHT_LIMIT = 500.0;

    public static void checkIfDroneCanMakeADelivery(Drone drone){
        Validate.isTrue(drone.getState().equals(State.IDLE), String.format("Drone %s is not available, it is %s", drone.getSerialNumber(), drone.getState()));
        Validate.isTrue(drone.getBatteryCapacity() > BATTERY_PERCENTAGE_LIMIT, String.format("Drone %s has a percentage below %s", drone.getSerialNumber(), BATTERY_PERCENTAGE_LIMIT));
    }

    public static void checkIfLoadDroneWeightCanBeDelivered(LoadDrone loadDrone){
        Validate.isTrue(loadDrone.getDroneLoadTotalWeight() <= WEIGHT_LIMIT, String.format("The total weight of this medication load exceeds %s gr", WEIGHT_LIMIT));
    }
}
