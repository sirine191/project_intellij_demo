package com.springdemo.ipvproject.entity; // Assurez-vous que le package est correct

public enum ModePaiement {

    MENSUALITES("PROLOC_ELEC: Paiement en plusieurs mensualités"),
    CASH("HORS_PROLOC_ELEC: Paiement en une seule fois");

    private final String description;

    ModePaiement(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
