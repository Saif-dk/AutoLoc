package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.exception.RessourceIntrouvableException;
import tn.esprit.autoloc.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService implements ClientInterface {

    private final ClientRepository clientRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Client> trouverTous() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Client trouverParId(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Client", id));
    }

    @Override
    public Client enregistrer(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client modifier(Long id, Client client) {
        Client existant = trouverParId(id);
        existant.setNom(client.getNom());
        existant.setPrenom(client.getPrenom());
        existant.setEmail(client.getEmail());
        existant.setTelephone(client.getTelephone());
        existant.setNumPermis(client.getNumPermis());
        existant.setDateInscription(client.getDateInscription());
        return clientRepository.save(existant);
    }

    @Override
    public void supprimer(Long id) {
        Client existant = trouverParId(id);
        clientRepository.delete(existant);
    }
}
