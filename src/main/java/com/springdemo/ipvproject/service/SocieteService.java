package com.springdemo.ipvproject.service;

import com.springdemo.ipvproject.dto.SocieteDTO;
import com.springdemo.ipvproject.entity.Societe;
import com.springdemo.ipvproject.repository.SocieteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class SocieteService {

    @Autowired
    private SocieteRepository societeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String encoderPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public List<Societe> getAllSocietes() {
        return societeRepository.findAll();
    }

    public List<Societe> searchSocietes(String keyword) {
        return societeRepository.findByNomContainingOrAdresseContaining(keyword, keyword);
    }

    // Inscription d'une nouvelle société
    public Societe registerSociete(SocieteDTO societeDTO) {
        Societe societe = new Societe();
        societe.setNom(societeDTO.getNom());
        societe.setAdresse(societeDTO.getAdresse());
        societe.setContact(societeDTO.getContact());
        societe.setEmail(societeDTO.getEmail());
        societe.setMotDePasse(passwordEncoder.encode(societeDTO.getMotDePasse())); // Hash du mot de passe

        return societeRepository.save(societe);
    }

    // Authentification de la société
    public Societe authenticateSociete(String mail, String motDePasse) {
        Optional<Societe> societeOptional = societeRepository.findByEmail(mail);
        if (societeOptional.isPresent()) {
            Societe societe = societeOptional.get();
            if (passwordEncoder.matches(motDePasse, societe.getMotDePasse())) {
                return societe;
            }
        }
        return null;
    }
}
