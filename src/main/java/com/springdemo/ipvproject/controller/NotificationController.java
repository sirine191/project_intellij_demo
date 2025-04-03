package com.springdemo.ipvproject.controller;

import com.springdemo.ipvproject.entity.Notification;
import com.springdemo.ipvproject.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/client/{clientId}")
    public List<Notification> getNotificationsForClient(@PathVariable Long clientId) {
        return notificationService.getNotificationsForClient(clientId);
    }

    @GetMapping("/societe/{societeId}")
    public List<Notification> getNotificationsForSociete(@PathVariable Long societeId) {
        return notificationService.getNotificationsForSociete(societeId);
    }

    @PutMapping("/mark-as-read/{notificationId}")
    public void markAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
    }

    @PostMapping("/send")
    public void sendNotification(@RequestBody Notification notification) {
        notificationService.sendNotification(notification);
    }
}
