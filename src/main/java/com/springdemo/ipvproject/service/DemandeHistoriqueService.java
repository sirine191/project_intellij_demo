package com.springdemo.ipvproject.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.springdemo.ipvproject.entity.Client;
import com.springdemo.ipvproject.entity.DemandeHistorique;
import com.springdemo.ipvproject.entity.Societe;
import com.springdemo.ipvproject.repository.ClientRepository;
import com.springdemo.ipvproject.repository.DemandeHistoriqueRepository;
import com.springdemo.ipvproject.repository.SocieteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DemandeHistoriqueService {

    @Autowired
    private DemandeHistoriqueRepository demandeHistoriqueRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SocieteRepository societeRepository;

    public DemandeHistorique creerDemande(Long clientId, Long societeId) {
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        Optional<Societe> societeOpt = societeRepository.findById(societeId);

        if (clientOpt.isPresent() && societeOpt.isPresent()) {
            DemandeHistorique demande = new DemandeHistorique(clientOpt.get(), societeOpt.get());
            return demandeHistoriqueRepository.save(demande);
        }
        throw new RuntimeException("Client ou Société introuvable !");
    }

    public List<DemandeHistorique> obtenirDemandesParClient(Long clientId) {
        return demandeHistoriqueRepository.findByClientId(clientId);
    }

    public List<DemandeHistorique> obtenirDemandesParSociete(Long societeId) {
        return demandeHistoriqueRepository.findBySocieteId(societeId);
    }

    public DemandeHistorique validerDemande(Long demandeId) {
        Optional<DemandeHistorique> demandeOpt = demandeHistoriqueRepository.findById(demandeId);
        if (demandeOpt.isPresent()) {
            DemandeHistorique demande = demandeOpt.get();
            demande.setRemplie(true);
            return demandeHistoriqueRepository.save(demande);
        }
        throw new RuntimeException("Demande introuvable !");
    }

    public byte[] genererPdfDemande(Long demandeId) throws IOException {
        Optional<DemandeHistorique> demandeOpt = demandeHistoriqueRepository.findById(demandeId);
        if (!demandeOpt.isPresent()) {
            throw new RuntimeException("Demande introuvable !");
        }

        DemandeHistorique demande = demandeOpt.get();
        Client client = demande.getClient();
        Societe societe = demande.getSociete();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Demande Historique"));
        document.add(new Paragraph("----------------------------"));
        document.add(new Paragraph("Client: " + client.getNom() + " " + client.getPrenom()));
        document.add(new Paragraph("Société: " + societe.getNom()));
        document.add(new Paragraph("Statut: " + (demande.isRemplie() ? "Validée" : "En attente")));

        document.close();
        return baos.toByteArray();
    }
}
