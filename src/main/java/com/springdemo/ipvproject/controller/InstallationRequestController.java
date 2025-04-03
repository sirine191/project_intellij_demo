package com.springdemo.ipvproject.controller;

import com.springdemo.ipvproject.dto.InstallationRequestDTO;
import com.springdemo.ipvproject.entity.InstallationRequest;
import com.springdemo.ipvproject.service.InstallationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/installation")
public class InstallationRequestController {

    @Autowired
    private InstallationRequestService requestService;

    @PostMapping("/create")
    public InstallationRequest createRequest(@RequestBody InstallationRequestDTO requestDTO) {
        return requestService.createRequest(requestDTO.getClientEmail(), requestDTO.getDetails());
    }
}
