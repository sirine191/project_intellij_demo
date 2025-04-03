package com.springdemo.ipvproject.repository;

import com.springdemo.ipvproject.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Vérifier si un client existe par email
    Optional<Client> findByEmail(String email);

    // Trouver un client par son numéro de téléphone
    Optional<Client> findByTelephone(String telephone);

    // Rechercher les clients par nom ou prénom (IgnoreCase pour ne pas tenir compte de la casse)
    List<Client> findByNomIgnoreCaseOrPrenomIgnoreCase(String nom, String prenom);

    // Recherche avancée des clients par société
    @Query("SELECT c FROM Client c WHERE LOWER(c.societe.nom) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Client> searchBySociete(@Param("keyword") String keyword);

    // Vérifier si un client est déjà enregistré avec un CIN spécifique
    boolean existsByCin(String cin);

}
