package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.exception.RessourceIntrouvableException;
import tn.esprit.autoloc.repository.ClientRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements IClientService {

    ClientRepository clientRepository;

    public List<Client> retrieveAllClients() {
        return clientRepository.findAll();
    }

    public Client retrieveClient(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new RessourceIntrouvableException("Client", clientId));
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public void removeClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    public Client modifyClient(Client client) {
        return clientRepository.save(client);
    }
}
