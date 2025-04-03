package com.springdemo.ipvproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "societes")
@Email
@NotBlank
public class Societe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String adresse;
    private String contact;
    private String gouvernorat;
    @Column(unique = true, nullable = false)
    private String email;
    private String numAgrement;
    private Boolean validite;
    @Column(unique = true, nullable = false)
    private String MotDePasse;



    @OneToMany(mappedBy = "societe", cascade = CascadeType.ALL)
    private List<Client> clients;

    public Societe() {}

    public Societe(String nom, String adresse, String contact, String email, String MotDePasse) {
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
        this.email = email;
        this.MotDePasse = MotDePasse;
    }
}
