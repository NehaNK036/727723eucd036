package com.neha.springpro.model;

import jakarta.persistence.*;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String licenseNumber;
    private String phone;
    private String assignedBus;

    // Constructors
    public Driver() {}

    public Driver(String name, String licenseNumber, String phone, String assignedBus) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.phone = phone;
        this.assignedBus = assignedBus;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAssignedBus() { return assignedBus; }
    public void setAssignedBus(String assignedBus) { this.assignedBus = assignedBus; }
}

