package com.musala.drone_delivery.model.jpa;

import javax.persistence.*;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/24/22
 */
@Entity
@Table(name = "t_medication_load")
@Embeddable
public class MedicationLoad extends BaseEntity{
    private LoadDrone loadDrone;
    private Medication medication;
    private int quantity;
    private double medicationTotalWeight;

    @ManyToOne
    @JoinColumn(name = "load_drone_id")
    public LoadDrone getLoadDrone() {
        return loadDrone;
    }

    public void setLoadDrone(LoadDrone loadDrone) {
        this.loadDrone = loadDrone;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medication_code", referencedColumnName = "code")
    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(name = "medication_total_weight")
    public double getMedicationTotalWeight() {
        return medicationTotalWeight;
    }

    public void setMedicationTotalWeight(double medicationTotalWeight) {
        this.medicationTotalWeight = medicationTotalWeight;
    }
}
