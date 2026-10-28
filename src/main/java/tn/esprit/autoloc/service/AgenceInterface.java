package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface AgenceInterface {

    List<Agence> trouverTous();

    Agence trouverParId(Long id);

    Agence enregistrer(Agence agence);

    Agence modifier(Long id, Agence agence);

    void supprimer(Long id);
}
