package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContratDTO(
        Long idContrat,
        LocalDate dateSignature,
        @NotNull(message = "Le montant total est obligatoire")
        @PositiveOrZero(message = "Le montant total ne peut pas être négatif") BigDecimal montantTotal,
        boolean valide,
        @NotNull(message = "La réservation est obligatoire") Long reservationId) {
}
