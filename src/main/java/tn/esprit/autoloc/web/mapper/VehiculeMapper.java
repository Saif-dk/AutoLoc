package tn.esprit.autoloc.web.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IAgenceService;
import tn.esprit.autoloc.service.IEquipementService;
import tn.esprit.autoloc.web.dto.VehiculeDTO;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class VehiculeMapper {

    IAgenceService agenceService;
    IEquipementService equipementService;

    public VehiculeDTO toDto(Vehicule vehicule) {
        Long agenceId = vehicule.getAgence() != null ? vehicule.getAgence().getIdAgence() : null;
        Set<Long> equipementIds = vehicule.getEquipements().stream()
                .map(Equipement::getIdEquipement)
                .collect(Collectors.toSet());
        return new VehiculeDTO(
                vehicule.getIdVehicule(),
                vehicule.getImmatriculation(),
                vehicule.getMarque(),
                vehicule.getModele(),
                vehicule.getCategorie(),
                vehicule.getTarifJournalier(),
                vehicule.getStatut(),
                agenceId,
                equipementIds);
    }

    public Vehicule toEntity(VehiculeDTO dto) {
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation(dto.immatriculation());
        vehicule.setMarque(dto.marque());
        vehicule.setModele(dto.modele());
        vehicule.setCategorie(dto.categorie());
        vehicule.setTarifJournalier(dto.tarifJournalier());
        vehicule.setStatut(dto.statut() != null ? dto.statut() : StatutVehicule.DISPONIBLE);

        if (dto.agenceId() != null) {
            vehicule.setAgence(agenceService.retrieveAgence(dto.agenceId()));      // 404 si inexistante
        }

        Set<Equipement> equipements = new HashSet<>();
        if (dto.equipementIds() != null) {
            dto.equipementIds().forEach(id -> equipements.add(equipementService.retrieveEquipement(id)));
        }
        vehicule.setEquipements(equipements);
        return vehicule;
    }
}
