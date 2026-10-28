package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;

public interface MaintenanceInterface {

    List<Maintenance> trouverTous();

    Maintenance trouverParId(Long id);

    Maintenance enregistrer(Maintenance maintenance);

    Maintenance modifier(Long id, Maintenance maintenance);

    void supprimer(Long id);
}
