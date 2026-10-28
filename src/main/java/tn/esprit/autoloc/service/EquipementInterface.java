package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;

import java.util.List;

public interface EquipementInterface {

    List<Equipement> trouverTous();

    Equipement trouverParId(Long id);

    Equipement enregistrer(Equipement equipement);

    Equipement modifier(Long id, Equipement equipement);

    void supprimer(Long id);
}
