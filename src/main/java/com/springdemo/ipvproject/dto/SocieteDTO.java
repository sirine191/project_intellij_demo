package com.springdemo.ipvproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocieteDTO {

    private Long id;

    @NotBlank(message = "Le nom de la société est obligatoire")
    @Size(max = 100, message = "Le nom de la société ne doit pas dépasser 100 caractères")
    private String nom;

    @NotBlank(message = "L'adresse est obligatoire")
    @Size(max = 255, message = "L'adresse ne doit pas dépasser 255 caractères")
    private String adresse;

    @NotBlank(message = "Le contact est obligatoire")
    @Size(min = 10, max = 15, message = "Le contact doit être compris entre 10 et 15 caractères")
    private String contact;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format de l'email invalide")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
    private String motDePasse;
}
