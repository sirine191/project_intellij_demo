package com.springdemo.ipvproject.service;

import com.springdemo.ipvproject.dto.SocieteDTO;
import com.springdemo.ipvproject.entity.Societe;
import com.springdemo.ipvproject.repository.SocieteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private SocieteRepository societeRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Societe registerSociete(SocieteDTO societeDTO) {
        if (societeRepository.findByEmail(societeDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé !");
        }

        Societe societe = new Societe();
        societe.setNom(societeDTO.getNom());
        societe.setAdresse(societeDTO.getAdresse());
        societe.setContact(societeDTO.getContact());
        societe.setEmail(societeDTO.getEmail());
        societe.setMotDePasse(passwordEncoder.encode(societeDTO.getMotDePasse()));

        return societeRepository.save(societe);
    }

    public Optional<Societe> login(String email, String password) {
        Optional<Societe> societe = societeRepository.findByEmail(email);
        if (societe.isPresent() && passwordEncoder.matches(password, societe.get().getMotDePasse())) {
            return societe;
        }
        return Optional.empty();
    }
}
