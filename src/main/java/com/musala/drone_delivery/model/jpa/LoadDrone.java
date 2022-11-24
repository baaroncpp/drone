package com.musala.drone_delivery.model.jpa;

import javax.persistence.*;
import java.util.List;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/24/22
 */
@Entity
@Table(name = "t_load_drone")
public class LoadDrone extends BaseEntity{
    private Drone drone;
    private List<MedicationLoad> medicationLoads;
    private double DroneLoadTotalWeight;

    @ManyToOne
    @JoinColumn(name = "drone_serial_number", referencedColumnName = "serial_number")
    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    @OneToMany(mappedBy="loadDrone")
    @OrderBy("medication ASC")
    public List<MedicationLoad> getMedicationLoads() {
        return medicationLoads;
    }

    public void setMedicationLoads(List<MedicationLoad> medicationLoads) {
        this.medicationLoads = medicationLoads;
    }

    @Column(name = "drone_load_total_weight")
    public double getDroneLoadTotalWeight() {
        return DroneLoadTotalWeight;
    }

    public void setDroneLoadTotalWeight(double droneLoadTotalWeight) {
        DroneLoadTotalWeight = droneLoadTotalWeight;
    }
}
