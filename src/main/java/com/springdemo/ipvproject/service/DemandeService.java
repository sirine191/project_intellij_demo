package com.springdemo.ipvproject.service;

import com.springdemo.ipvproject.entity.Demande;
import com.springdemo.ipvproject.entity.Client;
import com.springdemo.ipvproject.entity.Societe;
import com.springdemo.ipvproject.entity.Notification;
import com.springdemo.ipvproject.repository.ClientRepository;
import com.springdemo.ipvproject.repository.DemandeRepository;
import com.springdemo.ipvproject.repository.NotificationRepository;
import com.springdemo.ipvproject.repository.SocieteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SocieteRepository societeRepository;

    public void creerDemande(Long clientId, Long societeId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        Societe societe = societeRepository.findById(societeId).orElseThrow(() -> new RuntimeException("Societe not found"));

        Demande demande = new Demande();
        demande.setClient(client);
        demande.setSociete(societe);
        demande.setEnAttente(true);

        demandeRepository.save(demande);

        // Notification pour la société
        Notification notification = new Notification();
        notification.setMessage("Vous avez une nouvelle demande de contact de " + client.getNom());
        notification.setClient(client);
        notification.setSociete(societe);
        notification.setVu(false);

        notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsForClient(Long clientId) {
        return notificationRepository.findByClientIdAndVu(clientId, false);
    }

    public List<Demande> getDemandesParSociete(Long societeId) {
        return demandeRepository.findBySocieteIdAndEnAttente(societeId, true);
    }

    public void repondreDemande(Long demandeId, boolean accepte) {
        Demande demande = demandeRepository.findById(demandeId).orElseThrow(() -> new RuntimeException("Demande not found"));
        demande.setAccepte(accepte);
        demande.setEnAttente(false);
        demandeRepository.save(demande);

        // Notification pour le client
        Notification notification = new Notification();
        notification.setMessage(accepte ? "Votre demande a été acceptée par la société." : "Votre demande a été rejetée par la société.");
        notification.setClient(demande.getClient());
        notification.setSociete(demande.getSociete());
        notification.setVu(false);

        notificationRepository.save(notification);
    }
}

