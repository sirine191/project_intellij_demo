package com.springdemo.ipvproject.controller;

import com.springdemo.ipvproject.entity.DemandeHistorique;
import com.springdemo.ipvproject.service.DemandeHistoriqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/demandes-historiques")
public class DemandeHistoriqueController {

    @Autowired
    private DemandeHistoriqueService demandeHistoriqueService;

    /**
     * 📌 Endpoint pour créer une demande
     */
    @PostMapping("/creer")
    public DemandeHistorique creerDemande(@RequestParam Long clientId, @RequestParam Long societeId) {
        return demandeHistoriqueService.creerDemande(clientId, societeId);
    }

    /**
     * 📌 Endpoint pour récupérer toutes les demandes d'un client
     */
    @GetMapping("/client/{clientId}")
    public List<DemandeHistorique> obtenirDemandesParClient(@PathVariable Long clientId) {
        return demandeHistoriqueService.obtenirDemandesParClient(clientId);
    }

    /**
     * 📌 Endpoint pour récupérer toutes les demandes envoyées par une société
     */
    @GetMapping("/societe/{societeId}")
    public List<DemandeHistorique> obtenirDemandesParSociete(@PathVariable Long societeId) {
        return demandeHistoriqueService.obtenirDemandesParSociete(societeId);
    }

    /**
     * 📌 Endpoint pour valider une demande après que le client l'ait remplie
     */
    @PutMapping("/valider/{demandeId}")
    public DemandeHistorique validerDemande(@PathVariable Long demandeId) {
        return demandeHistoriqueService.validerDemande(demandeId);
    }

    /**
     * 📌 Endpoint pour générer un PDF d'une demande et le télécharger
     */
    @GetMapping("/pdf/{demandeId}")
    public ResponseEntity<byte[]> genererPdfDemande(@PathVariable Long demandeId) throws IOException {
        byte[] pdfData = demandeHistoriqueService.genererPdfDemande(demandeId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=demande_" + demandeId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfData);
    }
}
