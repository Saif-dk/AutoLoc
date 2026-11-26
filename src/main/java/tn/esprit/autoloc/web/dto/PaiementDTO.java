package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import tn.esprit.autoloc.domain.ModePaiement;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaiementDTO(
        Long idPaiement,
        @NotNull(message = "Le montant est obligatoire")
        @Positive(message = "Le montant doit être positif") BigDecimal montant,
        LocalDate datePaiement,
        @NotNull(message = "Le mode de paiement est obligatoire") ModePaiement modePaiement,
        @NotNull(message = "Le contrat est obligatoire") Long contratId) {
}
