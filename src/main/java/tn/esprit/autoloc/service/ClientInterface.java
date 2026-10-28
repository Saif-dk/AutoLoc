package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;

import java.util.List;

public interface ClientInterface {

    List<Client> trouverTous();

    Client trouverParId(Long id);

    Client enregistrer(Client client);

    Client modifier(Long id, Client client);

    void supprimer(Long id);
}
