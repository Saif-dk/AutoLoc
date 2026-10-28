package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;

public interface IPaiementService {

    List<Paiement> retrieveAllPaiements();

    Paiement retrievePaiement(Long paiementId);

    Paiement addPaiement(Paiement paiement);

    void removePaiement(Long paiementId);

    Paiement modifyPaiement(Paiement paiement);
}
