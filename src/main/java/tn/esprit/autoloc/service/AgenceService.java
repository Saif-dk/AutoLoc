package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.exception.RessourceIntrouvableException;
import tn.esprit.autoloc.repository.AgenceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AgenceService implements AgenceInterface {

    private final AgenceRepository agenceRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Agence> trouverTous() {
        return agenceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Agence trouverParId(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Agence", id));
    }

    @Override
    public Agence enregistrer(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public Agence modifier(Long id, Agence agence) {
        Agence existant = trouverParId(id);
        existant.setNom(agence.getNom());
        existant.setVille(agence.getVille());
        existant.setAdresse(agence.getAdresse());
        existant.setTelephone(agence.getTelephone());
        return agenceRepository.save(existant);
    }

    @Override
    public void supprimer(Long id) {
        Agence existant = trouverParId(id);
        agenceRepository.delete(existant);
    }
}
