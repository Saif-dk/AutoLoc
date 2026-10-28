package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.exception.RessourceIntrouvableException;
import tn.esprit.autoloc.repository.AgenceRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    AgenceRepository agenceRepository;

    public List<Agence> retrieveAllAgences() {
        return agenceRepository.findAll();
    }

    public Agence retrieveAgence(Long agenceId) {
        return agenceRepository.findById(agenceId)
                .orElseThrow(() -> new RessourceIntrouvableException("Agence", agenceId));
    }

    public Agence addAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    public void removeAgence(Long agenceId) {
        agenceRepository.deleteById(agenceId);
    }

    public Agence modifyAgence(Agence agence) {
        return agenceRepository.save(agence);
    }
}