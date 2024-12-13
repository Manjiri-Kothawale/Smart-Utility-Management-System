package com.manjiri.smart_utility_management;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name = "ServicePerson")
public class ServicePerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 1)
    private Long id;
    private String name;
    private String contact;
    private String email;
    private String serviceType;
    private String availabilityTime;
    private String additionalDetails;

    // Default constructor
    public ServicePerson() {
    }

    // Parameterized constructor
    public ServicePerson(String name, String contact, String email, String serviceType, String availabilityTime, String additionalDetails) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.serviceType = serviceType;
        this.availabilityTime = availabilityTime;
        this.additionalDetails = additionalDetails;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getAvailabilityTime() {
        return availabilityTime;
    }

    public void setAvailabilityTime(String availabilityTime) {
        this.availabilityTime = availabilityTime;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }

    @Override
    public String toString() {
        return "ServicePerson [id=" + id + ", name=" + name + ", contact=" + contact + ", email=" + email
                + ", serviceType=" + serviceType + ", availabilityTime=" + availabilityTime + ", additionalDetails="
                + additionalDetails + "]";
    }
}
