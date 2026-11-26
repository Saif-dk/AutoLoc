package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ClientDTO(
        Long idClient,
        @NotBlank(message = "Le nom est obligatoire") String nom,
        @NotBlank(message = "Le prénom est obligatoire") String prenom,
        @NotBlank(message = "L'email est obligatoire")
        @Email(message = "L'email n'est pas valide") String email,
        String telephone,
        @NotBlank(message = "Le numéro de permis est obligatoire") String numPermis,
        LocalDate dateInscription) {
}
