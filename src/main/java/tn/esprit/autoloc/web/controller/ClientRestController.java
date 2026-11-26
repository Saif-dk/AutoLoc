package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;
import tn.esprit.autoloc.web.dto.ClientDTO;
import tn.esprit.autoloc.web.mapper.ClientMapper;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientRestController {

    IClientService clientService;
    ClientMapper clientMapper;

    // http://localhost:8080/client/retrieve-all-clients
    @GetMapping("/retrieve-all-clients")
    public List<ClientDTO> getClients() {
        return clientService.retrieveAllClients().stream()
                .map(clientMapper::toDto)
                .toList();
    }

    // http://localhost:8080/client/retrieve-client/1
    @GetMapping("/retrieve-client/{client-id}")
    public ClientDTO retrieveClient(@PathVariable("client-id") Long clientId) {
        return clientMapper.toDto(clientService.retrieveClient(clientId));
    }

    // http://localhost:8080/client/add-client
    @PostMapping("/add-client")
    public ClientDTO addClient(@Valid @RequestBody ClientDTO dto) {
        return clientMapper.toDto(clientService.addClient(clientMapper.toEntity(dto)));
    }

    // http://localhost:8080/client/remove-client/1
    @DeleteMapping("/remove-client/{client-id}")
    public void removeClient(@PathVariable("client-id") Long clientId) {
        clientService.removeClient(clientId);
    }

    // http://localhost:8080/client/modify-client
    @PutMapping("/modify-client")
    public ClientDTO modifyClient(@Valid @RequestBody ClientDTO dto) {
        if (dto.idClient() == null) {
            throw new IllegalArgumentException("idClient est obligatoire pour la modification");
        }
        clientService.retrieveClient(dto.idClient());          // 404 si l'élément n'existe pas
        Client entity = clientMapper.toEntity(dto);
        entity.setIdClient(dto.idClient());
        return clientMapper.toDto(clientService.modifyClient(entity));
    }
}
