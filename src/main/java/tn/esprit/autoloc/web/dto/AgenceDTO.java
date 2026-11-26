package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.NotBlank;

public record AgenceDTO(
        Long idAgence,
        @NotBlank(message = "Le nom est obligatoire") String nom,
        @NotBlank(message = "La ville est obligatoire") String ville,
        String adresse,
        String telephone) {
}
