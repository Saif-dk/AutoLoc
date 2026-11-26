package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiementService;
import tn.esprit.autoloc.web.dto.PaiementDTO;
import tn.esprit.autoloc.web.mapper.PaiementMapper;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/paiement")
public class PaiementRestController {

    IPaiementService paiementService;
    PaiementMapper paiementMapper;

    // http://localhost:8080/paiement/retrieve-all-paiements
    @GetMapping("/retrieve-all-paiements")
    public List<PaiementDTO> getPaiements() {
        return paiementService.retrieveAllPaiements().stream()
                .map(paiementMapper::toDto)
                .toList();
    }

    // http://localhost:8080/paiement/retrieve-paiement/1
    @GetMapping("/retrieve-paiement/{paiement-id}")
    public PaiementDTO retrievePaiement(@PathVariable("paiement-id") Long paiementId) {
        return paiementMapper.toDto(paiementService.retrievePaiement(paiementId));
    }

    // http://localhost:8080/paiement/add-paiement
    @PostMapping("/add-paiement")
    public PaiementDTO addPaiement(@Valid @RequestBody PaiementDTO dto) {
        return paiementMapper.toDto(paiementService.addPaiement(paiementMapper.toEntity(dto)));
    }

    // http://localhost:8080/paiement/remove-paiement/1
    @DeleteMapping("/remove-paiement/{paiement-id}")
    public void removePaiement(@PathVariable("paiement-id") Long paiementId) {
        paiementService.removePaiement(paiementId);
    }

    // http://localhost:8080/paiement/modify-paiement
    @PutMapping("/modify-paiement")
    public PaiementDTO modifyPaiement(@Valid @RequestBody PaiementDTO dto) {
        if (dto.idPaiement() == null) {
            throw new IllegalArgumentException("idPaiement est obligatoire pour la modification");
        }
        paiementService.retrievePaiement(dto.idPaiement());          // 404 si l'élément n'existe pas
        Paiement entity = paiementMapper.toEntity(dto);
        entity.setIdPaiement(dto.idPaiement());
        return paiementMapper.toDto(paiementService.modifyPaiement(entity));
    }
}
