package com.musala.drone_delivery.model.jpa;

import com.musala.drone_delivery.model.enums.Model;
import com.musala.drone_delivery.model.enums.State;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/24/22
 */
@Entity
@Table(name = "t_drone")
public class Drone extends BaseEntity{
    private String serialNumber;
    private Model model;
    private double weightLimit;
    private double batteryCapacity;
    private State state;

    @Column(name = "serial_number")
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Column(name = "model")
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Column(name = "weight_limit")
    public double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(double weightLimit) {
        this.weightLimit = weightLimit;
    }

    @Column(name = "battery_capacity")
    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Column(name = "state")
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
