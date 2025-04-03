package com.springdemo.ipvproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean verified = false;

    @Column(nullable = false, unique = true)
    @Email(message = "L'email doit être valide")
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
    private String motdepasse;

    @Column(nullable = false)
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @Column(nullable = false)
    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @Column(nullable = false)
    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;

    @Column(nullable = false, unique = true)
    @Size(min = 8, max = 15, message = "Le numéro de téléphone doit être valide")
    private String telephone;

    @Column(nullable = false, unique = true)
    @Size(min = 8, max = 12, message = "Le CIN doit être valide")
    private String cin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModePaiement modePaiement;

    private boolean demandeEnAttente = true;
    private boolean notificationStatus = false;

    // Ajout des dates
    @Column(nullable = false, updatable = false)
    private LocalDate dateCreation;

    @Column(nullable = false)
    private LocalDate dateLimiteRemplissage;

    // Relation avec Societe
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "societe_id", nullable = false)
    private Societe societe;

    // Définition automatique des dates lors de la création
    @PrePersist
    protected void onCreate() {
        this.dateCreation = LocalDate.now();
        this.dateLimiteRemplissage = this.dateCreation.plusDays(2);
        this.demandeEnAttente = true;
    }

    // Mise à jour de la date limite lors de modifications importantes
    @PreUpdate
    protected void onUpdate() {
        if (this.demandeEnAttente) {
            this.dateLimiteRemplissage = LocalDate.now().plusDays(2);
        }
    }
}
