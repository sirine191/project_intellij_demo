package com.springdemo.ipvproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "demandes_historiques")
public class DemandeHistorique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client; // Association avec un client

    @ManyToOne
    @JoinColumn(name = "societe_id", nullable = false)
    private Societe societe; // Association avec une société

    @Column(nullable = false)
    private LocalDateTime dateCreation = LocalDateTime.now(); // Date de création

    @Column(nullable = false)
    private LocalDateTime dateLimite = LocalDateTime.now().plusDays(2); // Délai de 2 jours

    @Column(nullable = false)
    private boolean remplie = false; // Si la demande est remplie ou non

    // Constructeur par défaut
    public DemandeHistorique() {}

    // Constructeur avec remplissage automatique des données
    public DemandeHistorique(Client client, Societe societe) {
        this.client = client;
        this.societe = societe;
        this.dateCreation = LocalDateTime.now();
        this.dateLimite = this.dateCreation.plusDays(2);
    }
}
