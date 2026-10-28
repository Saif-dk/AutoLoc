package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;

import java.util.List;

public interface ContratInterface {

    List<Contrat> trouverTous();

    Contrat trouverParId(Long id);

    Contrat enregistrer(Contrat contrat);

    Contrat modifier(Long id, Contrat contrat);

    void supprimer(Long id);
}
