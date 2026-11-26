package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;
import tn.esprit.autoloc.web.dto.EquipementDTO;
import tn.esprit.autoloc.web.mapper.EquipementMapper;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipement")
public class EquipementRestController {

    IEquipementService equipementService;
    EquipementMapper equipementMapper;

    // http://localhost:8080/equipement/retrieve-all-equipements
    @GetMapping("/retrieve-all-equipements")
    public List<EquipementDTO> getEquipements() {
        return equipementService.retrieveAllEquipements().stream()
                .map(equipementMapper::toDto)
                .toList();
    }

    // http://localhost:8080/equipement/retrieve-equipement/1
    @GetMapping("/retrieve-equipement/{equipement-id}")
    public EquipementDTO retrieveEquipement(@PathVariable("equipement-id") Long equipementId) {
        return equipementMapper.toDto(equipementService.retrieveEquipement(equipementId));
    }

    // http://localhost:8080/equipement/add-equipement
    @PostMapping("/add-equipement")
    public EquipementDTO addEquipement(@Valid @RequestBody EquipementDTO dto) {
        return equipementMapper.toDto(equipementService.addEquipement(equipementMapper.toEntity(dto)));
    }

    // http://localhost:8080/equipement/remove-equipement/1
    @DeleteMapping("/remove-equipement/{equipement-id}")
    public void removeEquipement(@PathVariable("equipement-id") Long equipementId) {
        equipementService.removeEquipement(equipementId);
    }

    // http://localhost:8080/equipement/modify-equipement
    @PutMapping("/modify-equipement")
    public EquipementDTO modifyEquipement(@Valid @RequestBody EquipementDTO dto) {
        if (dto.idEquipement() == null) {
            throw new IllegalArgumentException("idEquipement est obligatoire pour la modification");
        }
        equipementService.retrieveEquipement(dto.idEquipement());          // 404 si l'élément n'existe pas
        Equipement entity = equipementMapper.toEntity(dto);
        entity.setIdEquipement(dto.idEquipement());
        return equipementMapper.toDto(equipementService.modifyEquipement(entity));
    }
}
