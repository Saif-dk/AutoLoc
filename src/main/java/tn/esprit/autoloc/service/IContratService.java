package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;

import java.util.List;

public interface IContratService {

    List<Contrat> retrieveAllContrats();

    Contrat retrieveContrat(Long contratId);

    Contrat addContrat(Contrat contrat);

    void removeContrat(Long contratId);

    Contrat modifyContrat(Contrat contrat);
}
