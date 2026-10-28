package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.exception.RessourceIntrouvableException;
import tn.esprit.autoloc.repository.PaiementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaiementService implements PaiementInterface {

    private final PaiementRepository paiementRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Paiement> trouverTous() {
        return paiementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Paiement trouverParId(Long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Paiement", id));
    }

    @Override
    public Paiement enregistrer(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement modifier(Long id, Paiement paiement) {
        Paiement existant = trouverParId(id);
        existant.setMontant(paiement.getMontant());
        existant.setDatePaiement(paiement.getDatePaiement());
        existant.setModePaiement(paiement.getModePaiement());
        return paiementRepository.save(existant);
    }

    @Override
    public void supprimer(Long id) {
        Paiement existant = trouverParId(id);
        paiementRepository.delete(existant);
    }
}
