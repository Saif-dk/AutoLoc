package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;
import tn.esprit.autoloc.web.dto.VehiculeDTO;
import tn.esprit.autoloc.web.mapper.VehiculeMapper;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vehicule")
public class VehiculeRestController {

    IVehiculeService vehiculeService;
    VehiculeMapper vehiculeMapper;

    // http://localhost:8080/vehicule/retrieve-all-vehicules
    @GetMapping("/retrieve-all-vehicules")
    public List<VehiculeDTO> getVehicules() {
        return vehiculeService.retrieveAllVehicules().stream()
                .map(vehiculeMapper::toDto)
                .toList();
    }

    // http://localhost:8080/vehicule/retrieve-vehicule/1
    @GetMapping("/retrieve-vehicule/{vehicule-id}")
    public VehiculeDTO retrieveVehicule(@PathVariable("vehicule-id") Long vehiculeId) {
        return vehiculeMapper.toDto(vehiculeService.retrieveVehicule(vehiculeId));
    }

    // http://localhost:8080/vehicule/add-vehicule
    @PostMapping("/add-vehicule")
    public VehiculeDTO addVehicule(@Valid @RequestBody VehiculeDTO dto) {
        return vehiculeMapper.toDto(vehiculeService.addVehicule(vehiculeMapper.toEntity(dto)));
    }

    // http://localhost:8080/vehicule/remove-vehicule/1
    @DeleteMapping("/remove-vehicule/{vehicule-id}")
    public void removeVehicule(@PathVariable("vehicule-id") Long vehiculeId) {
        vehiculeService.removeVehicule(vehiculeId);
    }

    // http://localhost:8080/vehicule/modify-vehicule
    @PutMapping("/modify-vehicule")
    public VehiculeDTO modifyVehicule(@Valid @RequestBody VehiculeDTO dto) {
        if (dto.idVehicule() == null) {
            throw new IllegalArgumentException("idVehicule est obligatoire pour la modification");
        }
        vehiculeService.retrieveVehicule(dto.idVehicule());          // 404 si l'élément n'existe pas
        Vehicule entity = vehiculeMapper.toEntity(dto);
        entity.setIdVehicule(dto.idVehicule());
        return vehiculeMapper.toDto(vehiculeService.modifyVehicule(entity));
    }
}
