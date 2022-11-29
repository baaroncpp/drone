package com.musala.drone_delivery.model.jpa;

import javax.persistence.*;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/29/22
 */
@Entity
@Table(name = "t_battery_history")
public class BatteryHistory extends BaseEntity{
    private Drone drone;
    private double currentBatteryCapacity;

    @ManyToOne
    @JoinColumn(name = "drone_serial_number", referencedColumnName = "serial_number")
    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    @Column(name = "current_battery_capacity")
    public double getCurrentBatteryCapacity() {
        return currentBatteryCapacity;
    }

    public void setCurrentBatteryCapacity(double currentBatteryCapacity) {
        this.currentBatteryCapacity = currentBatteryCapacity;
    }
}
