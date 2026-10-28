package tn.esprit.autoloc.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientRestController {

    IClientService clientService;

    // http://localhost:8080/client/retrieve-all-clients
    @GetMapping("/retrieve-all-clients")
    public List<Client> getClients() {
        return clientService.retrieveAllClients();
    }

    // http://localhost:8080/client/retrieve-client/1
    @GetMapping("/retrieve-client/{client-id}")
    public Client retrieveClient(@PathVariable("client-id") Long clientId) {
        return clientService.retrieveClient(clientId);
    }

    // http://localhost:8080/client/add-client
    @PostMapping("/add-client")
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    // http://localhost:8080/client/remove-client/1
    @DeleteMapping("/remove-client/{client-id}")
    public void removeClient(@PathVariable("client-id") Long clientId) {
        clientService.removeClient(clientId);
    }

    // http://localhost:8080/client/modify-client
    @PutMapping("/modify-client")
    public Client modifyClient(@RequestBody Client client) {
        return clientService.modifyClient(client);
    }
}
