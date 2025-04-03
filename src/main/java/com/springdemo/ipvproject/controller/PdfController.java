package com.springdemo.ipvproject.controller;

import com.springdemo.ipvproject.service.DemandeHistoriqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private DemandeHistoriqueService demandeHistoriqueService;

    /**
     * 📌 Générer et télécharger un PDF pour une demande spécifique
     */
    @GetMapping("/demande/{demandeId}")
    public ResponseEntity<byte[]> genererPdf(@PathVariable Long demandeId) {
        try {
            byte[] pdfBytes = demandeHistoriqueService.genererPdfDemande(demandeId);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=demande_" + demandeId + ".pdf");
            headers.add("Content-Type", "application/pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
