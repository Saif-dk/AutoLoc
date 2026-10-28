package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;

public interface PaiementInterface {

    List<Paiement> trouverTous();

    Paiement trouverParId(Long id);

    Paiement enregistrer(Paiement paiement);

    Paiement modifier(Long id, Paiement paiement);

    void supprimer(Long id);
}
