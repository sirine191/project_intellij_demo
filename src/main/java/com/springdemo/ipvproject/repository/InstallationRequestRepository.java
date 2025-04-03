package com.springdemo.ipvproject.repository;

import com.springdemo.ipvproject.entity.InstallationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface InstallationRequestRepository extends JpaRepository<InstallationRequest, Long> {
    List<InstallationRequest> findByClientEmail(String clientEmail);
    Optional<InstallationRequest> findById(Long id);
}
