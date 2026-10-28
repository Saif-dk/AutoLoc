package tn.esprit.autoloc.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.AgenceInterface;
import tn.esprit.autoloc.service.AgenceService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/agence")
public class AgenceController {
    AgenceInterface ag;

    @PostMapping("/add-projet")
    public Agence addAgence(@RequestBody Agence c) { Agence agence = AgenceService.addAgence(c); return
            agence;
    }

    @GetMapping("/retrieve-all-agences")
    public List<Agence> getProjets() {
        List<Agence> listAgence = ag.retrieveAllAgence();
        return listAgence;
    }
}


