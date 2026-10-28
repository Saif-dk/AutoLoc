package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.exception.RessourceIntrouvableException;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VehiculeService implements VehiculeInterface {

    private final VehiculeRepository vehiculeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> trouverTous() {
        return vehiculeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Vehicule trouverParId(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Vehicule", id));
    }

    @Override
    public Vehicule enregistrer(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule modifier(Long id, Vehicule vehicule) {
        Vehicule existant = trouverParId(id);
        existant.setImmatriculation(vehicule.getImmatriculation());
        existant.setMarque(vehicule.getMarque());
        existant.setModele(vehicule.getModele());
        existant.setCategorie(vehicule.getCategorie());
        existant.setTarifJournalier(vehicule.getTarifJournalier());
        existant.setStatut(vehicule.getStatut());
        return vehiculeRepository.save(existant);
    }

    @Override
    public void supprimer(Long id) {
        Vehicule existant = trouverParId(id);
        vehiculeRepository.delete(existant);
    }
}
