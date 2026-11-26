package tn.esprit.autoloc.web.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.web.dto.AgenceDTO;

@Component
public class AgenceMapper {

    public AgenceDTO toDto(Agence agence) {
        return new AgenceDTO(
                agence.getIdAgence(),
                agence.getNom(),
                agence.getVille(),
                agence.getAdresse(),
                agence.getTelephone());
    }

    // L'identifiant n'est pas copié : il est géré par le contrôleur (ajout ou modification).
    public Agence toEntity(AgenceDTO dto) {
        Agence agence = new Agence();
        agence.setNom(dto.nom());
        agence.setVille(dto.ville());
        agence.setAdresse(dto.adresse());
        agence.setTelephone(dto.telephone());
        return agence;
    }
}
