package com.manjiri.smart_utility_management;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServiceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long servicePersonId;
    private Long userId;
    private String serviceType;
    private Date serviceDate;

    // Default constructor
    public ServiceLog() {}

    // Parameterized constructor
    public ServiceLog(Long servicePersonId, Long userId, String serviceType, Date serviceDate) {
        this.servicePersonId = servicePersonId;
        this.userId = userId;
        this.serviceType = serviceType;
        this.serviceDate = serviceDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServicePersonId() {
        return servicePersonId;
    }

    public void setServicePersonId(Long servicePersonId) {
        this.servicePersonId = servicePersonId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    @Override
    public String toString() {
        return "ServiceLog [id=" + id + ", servicePersonId=" + servicePersonId + ", userId=" + userId
                + ", serviceType=" + serviceType + ", serviceDate=" + serviceDate + "]";
    }
}
