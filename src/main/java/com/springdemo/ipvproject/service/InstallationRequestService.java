package com.springdemo.ipvproject.service;

import com.springdemo.ipvproject.entity.InstallationRequest;
import com.springdemo.ipvproject.repository.InstallationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InstallationRequestService {

    @Autowired
    private InstallationRequestRepository requestRepository;

    public InstallationRequest createRequest(String clientEmail, String details) {
        InstallationRequest request = new InstallationRequest();
        request.setClientEmail(clientEmail);
        request.setStatus("Pending");
        request.setRequestDate(new Date());
        request.setInstallationDetails(details);

        return requestRepository.save(request);
    }
}
