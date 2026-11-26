package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MaintenanceDTO(
        Long idMaintenance,
        @NotNull(message = "La date de début est obligatoire") LocalDate dateDebut,
        LocalDate dateFin,
        String description,
        @NotNull(message = "Le véhicule est obligatoire") Long vehiculeId) {
}
