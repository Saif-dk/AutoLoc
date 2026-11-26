package tn.esprit.autoloc.web.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IAgenceService;
import tn.esprit.autoloc.web.dto.EmployeDTO;

@Component
@AllArgsConstructor
public class EmployeMapper {

    IAgenceService agenceService;

    public EmployeDTO toDto(Employe employe) {
        Long agenceId = employe.getAgence() != null ? employe.getAgence().getIdAgence() : null;
        return new EmployeDTO(
                employe.getIdEmploye(),
                employe.getNom(),
                employe.getPrenom(),
                employe.getRole(),
                agenceId);
    }

    public Employe toEntity(EmployeDTO dto) {
        Employe employe = new Employe();
        employe.setNom(dto.nom());
        employe.setPrenom(dto.prenom());
        employe.setRole(dto.role());
        if (dto.agenceId() != null) {
            employe.setAgence(agenceService.retrieveAgence(dto.agenceId()));      // 404 si inexistante
        }
        return employe;
    }
}
