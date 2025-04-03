package com.springdemo.ipvproject.repository;

import com.springdemo.ipvproject.entity.DemandeHistorique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DemandeHistoriqueRepository extends JpaRepository<DemandeHistorique, Long> {
    List<DemandeHistorique> findByClientId(Long clientId);
    List<DemandeHistorique> findBySocieteId(Long societeId);
    List<DemandeHistorique> findByRemplieFalseAndDateLimiteBefore(LocalDateTime date);
}
