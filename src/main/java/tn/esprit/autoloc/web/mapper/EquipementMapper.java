package tn.esprit.autoloc.web.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.web.dto.EquipementDTO;

@Component
public class EquipementMapper {

    public EquipementDTO toDto(Equipement equipement) {
        return new EquipementDTO(equipement.getIdEquipement(), equipement.getLibelle());
    }

    public Equipement toEntity(EquipementDTO dto) {
        Equipement equipement = new Equipement();
        equipement.setLibelle(dto.libelle());
        return equipement;
    }
}
