package com.springdemo.ipvproject.repository;

import com.springdemo.ipvproject.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Recherche des notifications non lues pour un client
    List<Notification> findByClientIdAndVu(Long clientId, boolean vu);

    // Recherche des notifications par client
    List<Notification> findByClientId(Long clientId);

    // Recherche des notifications par société
    List<Notification> findBySocieteId(Long societeId);
}
