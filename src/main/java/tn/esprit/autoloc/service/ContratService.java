package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.exception.RessourceIntrouvableException;
import tn.esprit.autoloc.repository.ContratRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ContratService implements ContratInterface {

    private final ContratRepository contratRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Contrat> trouverTous() {
        return contratRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Contrat trouverParId(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Contrat", id));
    }

    @Override
    public Contrat enregistrer(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat modifier(Long id, Contrat contrat) {
        Contrat existant = trouverParId(id);
        existant.setDateSignature(contrat.getDateSignature());
        existant.setMontantTotal(contrat.getMontantTotal());
        existant.setValide(contrat.isValide());
        return contratRepository.save(existant);
    }

    @Override
    public void supprimer(Long id) {
        Contrat existant = trouverParId(id);
        contratRepository.delete(existant);
    }
}
