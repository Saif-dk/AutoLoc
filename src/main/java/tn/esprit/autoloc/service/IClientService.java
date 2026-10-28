package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;

import java.util.List;

public interface IClientService {

    List<Client> retrieveAllClients();

    Client retrieveClient(Long clientId);

    Client addClient(Client client);

    void removeClient(Long clientId);

    Client modifyClient(Client client);
}
