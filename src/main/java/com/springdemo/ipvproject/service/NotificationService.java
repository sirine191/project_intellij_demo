package com.springdemo.ipvproject.service;

import com.springdemo.ipvproject.entity.Notification;
import com.springdemo.ipvproject.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Récupérer les notifications non lues pour un client donné
    public List<Notification> getNotificationsForClient(Long clientId) {
        // Recherche les notifications non lues pour un client spécifique
        return notificationRepository.findByClientIdAndVu(clientId, false);
    }

    // Récupérer toutes les notifications pour une société donnée
    public List<Notification> getNotificationsForSociete(Long societeId) {
        // Recherche toutes les notifications pour une société spécifique
        return notificationRepository.findBySocieteId(societeId);
    }

    // Marquer une notification comme lue
    public void markAsRead(Long notificationId) {
        // Cherche la notification par ID
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        // Met à jour le statut "vu" de la notification
        notification.setVu(true);

        // Sauvegarde la notification mise à jour
        notificationRepository.save(notification);
    }

    // Créer et envoyer une notification
    public void sendNotification(Notification notification) {
        // Enregistre la nouvelle notification
        notificationRepository.save(notification);
    }

}
