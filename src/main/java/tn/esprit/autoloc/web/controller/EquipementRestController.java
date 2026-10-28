package tn.esprit.autoloc.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipement")
public class EquipementRestController {

    IEquipementService equipementService;

    // http://localhost:8080/equipement/retrieve-all-equipements
    @GetMapping("/retrieve-all-equipements")
    public List<Equipement> getEquipements() {
        return equipementService.retrieveAllEquipements();
    }

    // http://localhost:8080/equipement/retrieve-equipement/1
    @GetMapping("/retrieve-equipement/{equipement-id}")
    public Equipement retrieveEquipement(@PathVariable("equipement-id") Long equipementId) {
        return equipementService.retrieveEquipement(equipementId);
    }

    // http://localhost:8080/equipement/add-equipement
    @PostMapping("/add-equipement")
    public Equipement addEquipement(@RequestBody Equipement equipement) {
        return equipementService.addEquipement(equipement);
    }

    // http://localhost:8080/equipement/remove-equipement/1
    @DeleteMapping("/remove-equipement/{equipement-id}")
    public void removeEquipement(@PathVariable("equipement-id") Long equipementId) {
        equipementService.removeEquipement(equipementId);
    }

    // http://localhost:8080/equipement/modify-equipement
    @PutMapping("/modify-equipement")
    public Equipement modifyEquipement(@RequestBody Equipement equipement) {
        return equipementService.modifyEquipement(equipement);
    }
}
