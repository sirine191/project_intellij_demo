package com.springdemo.ipvproject.repository;

import com.springdemo.ipvproject.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande, Long> {

    // Recherche des demandes en attente pour une société spécifique
    List<Demande> findBySocieteIdAndEnAttente(Long societeId, boolean enAttente);

    // Recherche des demandes par client
    List<Demande> findByClientId(Long clientId);

    // Recherche des demandes acceptées pour une société spécifique
    List<Demande> findBySocieteIdAndAccepte(Long societeId, boolean accepte);
}
