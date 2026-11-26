package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.NotBlank;

public record EquipementDTO(
        Long idEquipement,
        @NotBlank(message = "Le libellé est obligatoire") String libelle) {
}
