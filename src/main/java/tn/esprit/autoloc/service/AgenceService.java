package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.AgenceRepository;

import java.util.List;

public class AgenceService implements AgenceInterface{
    @Override
    public List<Agence> retrieveAllAgence() {
        return AgenceRepository.retrieveAll();
    }

    @Override
    public Agence retrieveAgence(Long pagenceId) {
        return null;
    }

    @Override
    public static Agence addAgence(Agence c) {
        return null;
    }

    @Override
    public void removeAgence(Long AgenceId) {

    }

    @Override
    public Agence modifyAgence(Agence projet) {
        return null;
    }
}
