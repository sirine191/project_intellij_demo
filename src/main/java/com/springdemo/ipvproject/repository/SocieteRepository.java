package com.springdemo.ipvproject.repository;

import com.springdemo.ipvproject.entity.Societe;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SocieteRepository extends JpaRepository<Societe, Long> {

    // Recherche une société par son ID
    Optional<Societe> findById(Long id);

    // Recherche une société par son email
    Optional<Societe> findByEmail(String email);

    // Recherche des sociétés par leur nom ou adresse, en utilisant un mot-clé
    List<Societe> findByNomContainingOrAdresseContaining(String nom, String adresse);

    Optional<Object> findByNom(@NotBlank(message = "Le nom de la société est obligatoire") String societe);
}
