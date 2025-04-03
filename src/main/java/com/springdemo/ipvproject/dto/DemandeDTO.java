package com.springdemo.ipvproject.dto;

import lombok.Data;

@Data
public class DemandeDTO {
    private Long clientId;
    private Long societeId;
    private String statut;
}
