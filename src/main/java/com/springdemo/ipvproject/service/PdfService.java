package com.springdemo.ipvproject.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.springdemo.ipvproject.entity.DemandeHistorique;
import com.springdemo.ipvproject.repository.DemandeHistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class PdfService {

    @Autowired
    private DemandeHistoriqueRepository demandeHistoriqueRepository;

    public byte[] genererPdfDemande(Long demandeId) throws IOException {
        Optional<DemandeHistorique> demandeOpt = demandeHistoriqueRepository.findById(demandeId);
        if (!demandeOpt.isPresent()) {
            throw new RuntimeException("Demande introuvable !");
        }

        DemandeHistorique demande = demandeOpt.get();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Titre
        document.add(new Paragraph("Demande Historique").setBold().setFontSize(16));
        document.add(new Paragraph("--------------------------------").setFontSize(12));

        // Informations du client
        document.add(new Paragraph("Client: " + demande.getClient().getNom() + " " + demande.getClient().getPrenom()));
        document.add(new Paragraph("Société: " + demande.getSociete().getNom()));
        document.add(new Paragraph("Statut: " + (demande.isRemplie() ? "Validée" : "En attente")));

        document.close();
        return baos.toByteArray();
    }
}