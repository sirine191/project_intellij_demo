package com.springdemo.ipvproject.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class InstallationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientEmail;
    private String status;
    private Date requestDate;
    private String installationDetails;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getInstallationDetails() {
        return installationDetails;
    }

    public void setInstallationDetails(String installationDetails) {
        this.installationDetails = installationDetails;
    }
}
