package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;
import tn.esprit.autoloc.web.dto.ContratDTO;
import tn.esprit.autoloc.web.mapper.ContratMapper;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/contrat")
public class ContratRestController {

    IContratService contratService;
    ContratMapper contratMapper;

    // http://localhost:8080/contrat/retrieve-all-contrats
    @GetMapping("/retrieve-all-contrats")
    public List<ContratDTO> getContrats() {
        return contratService.retrieveAllContrats().stream()
                .map(contratMapper::toDto)
                .toList();
    }

    // http://localhost:8080/contrat/retrieve-contrat/1
    @GetMapping("/retrieve-contrat/{contrat-id}")
    public ContratDTO retrieveContrat(@PathVariable("contrat-id") Long contratId) {
        return contratMapper.toDto(contratService.retrieveContrat(contratId));
    }

    // http://localhost:8080/contrat/add-contrat
    @PostMapping("/add-contrat")
    public ContratDTO addContrat(@Valid @RequestBody ContratDTO dto) {
        return contratMapper.toDto(contratService.addContrat(contratMapper.toEntity(dto)));
    }

    // http://localhost:8080/contrat/remove-contrat/1
    @DeleteMapping("/remove-contrat/{contrat-id}")
    public void removeContrat(@PathVariable("contrat-id") Long contratId) {
        contratService.removeContrat(contratId);
    }

    // http://localhost:8080/contrat/modify-contrat
    @PutMapping("/modify-contrat")
    public ContratDTO modifyContrat(@Valid @RequestBody ContratDTO dto) {
        if (dto.idContrat() == null) {
            throw new IllegalArgumentException("idContrat est obligatoire pour la modification");
        }
        contratService.retrieveContrat(dto.idContrat());          // 404 si l'élément n'existe pas
        Contrat entity = contratMapper.toEntity(dto);
        entity.setIdContrat(dto.idContrat());
        return contratMapper.toDto(contratService.modifyContrat(entity));
    }
}
