package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;

import java.util.List;

public interface EmployeInterface {

    List<Employe> trouverTous();

    Employe trouverParId(Long id);

    Employe enregistrer(Employe employe);

    Employe modifier(Long id, Employe employe);

    void supprimer(Long id);
}
