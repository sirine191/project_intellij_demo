package com.springdemo.ipvproject.controller;

import com.springdemo.ipvproject.dto.DemandeDTO;
import com.springdemo.ipvproject.entity.Demande;
import com.springdemo.ipvproject.entity.Notification;
import com.springdemo.ipvproject.service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demande")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    @PostMapping("/creer")
    public ResponseEntity<String> creerDemande(@RequestBody DemandeDTO demandeDTO) {
        try {
            demandeService.creerDemande(demandeDTO.getClientId(), demandeDTO.getSocieteId());
            return ResponseEntity.ok("Demande créée avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la création de la demande: " + e.getMessage());
        }
    }

    @GetMapping("/en-attente/{societeId}")
    public ResponseEntity<List<Demande>> getDemandesEnAttente(@PathVariable Long societeId) {
        List<Demande> demandes = demandeService.getDemandesParSociete(societeId);
        return ResponseEntity.ok(demandes);
    }

    @PostMapping("/repondre")
    public ResponseEntity<String> repondreDemande(@RequestParam Long demandeId, @RequestParam boolean accepte) {
        try {
            demandeService.repondreDemande(demandeId, accepte);
            return ResponseEntity.ok("Réponse à la demande enregistrée.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la réponse à la demande: " + e.getMessage());
        }
    }

    @GetMapping("/notifications/{clientId}")
    public ResponseEntity<List<Notification>> getNotificationsClient(@PathVariable Long clientId) {
        List<Notification> notifications = demandeService.getNotificationsForClient(clientId);
        return ResponseEntity.ok(notifications);
    }
}
