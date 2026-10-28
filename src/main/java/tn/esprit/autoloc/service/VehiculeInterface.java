package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;

public interface VehiculeInterface {

    List<Vehicule> trouverTous();

    Vehicule trouverParId(Long id);

    Vehicule enregistrer(Vehicule vehicule);

    Vehicule modifier(Long id, Vehicule vehicule);

    void supprimer(Long id);
}
