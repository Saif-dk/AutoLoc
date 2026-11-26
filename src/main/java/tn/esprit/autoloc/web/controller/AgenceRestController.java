package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;
import tn.esprit.autoloc.web.dto.AgenceDTO;
import tn.esprit.autoloc.web.mapper.AgenceMapper;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/agence")
public class AgenceRestController {

    IAgenceService agenceService;
    AgenceMapper agenceMapper;

    // http://localhost:8080/agence/retrieve-all-agences
    @GetMapping("/retrieve-all-agences")
    public List<AgenceDTO> getAgences() {
        return agenceService.retrieveAllAgences().stream()
                .map(agenceMapper::toDto)
                .toList();
    }

    // http://localhost:8080/agence/retrieve-agence/1
    @GetMapping("/retrieve-agence/{agence-id}")
    public AgenceDTO retrieveAgence(@PathVariable("agence-id") Long agenceId) {
        return agenceMapper.toDto(agenceService.retrieveAgence(agenceId));
    }

    // http://localhost:8080/agence/add-agence
    @PostMapping("/add-agence")
    public AgenceDTO addAgence(@Valid @RequestBody AgenceDTO dto) {
        return agenceMapper.toDto(agenceService.addAgence(agenceMapper.toEntity(dto)));
    }

    // http://localhost:8080/agence/remove-agence/1
    @DeleteMapping("/remove-agence/{agence-id}")
    public void removeAgence(@PathVariable("agence-id") Long agenceId) {
        agenceService.removeAgence(agenceId);
    }

    // http://localhost:8080/agence/modify-agence
    @PutMapping("/modify-agence")
    public AgenceDTO modifyAgence(@Valid @RequestBody AgenceDTO dto) {
        if (dto.idAgence() == null) {
            throw new IllegalArgumentException("idAgence est obligatoire pour la modification");
        }
        agenceService.retrieveAgence(dto.idAgence());          // 404 si l'élément n'existe pas
        Agence entity = agenceMapper.toEntity(dto);
        entity.setIdAgence(dto.idAgence());
        return agenceMapper.toDto(agenceService.modifyAgence(entity));
    }
}
