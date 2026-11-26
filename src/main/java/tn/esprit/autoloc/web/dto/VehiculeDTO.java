package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;

import java.math.BigDecimal;
import java.util.Set;

public record VehiculeDTO(
        Long idVehicule,
        @NotBlank(message = "L'immatriculation est obligatoire")
        @Size(max = 20, message = "L'immatriculation ne doit pas dépasser 20 caractères") String immatriculation,
        @NotBlank(message = "La marque est obligatoire")
        @Size(max = 50, message = "La marque ne doit pas dépasser 50 caractères") String marque,
        @NotBlank(message = "Le modèle est obligatoire")
        @Size(max = 50, message = "Le modèle ne doit pas dépasser 50 caractères") String modele,
        @NotNull(message = "La catégorie est obligatoire") CategorieVehicule categorie,
        @NotNull(message = "Le tarif journalier est obligatoire")
        @Positive(message = "Le tarif journalier doit être positif") BigDecimal tarifJournalier,
        StatutVehicule statut,
        Long agenceId,
        Set<Long> equipementIds) {
}
