package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.exception.RessourceIntrouvableException;
import tn.esprit.autoloc.repository.PaiementRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    PaiementRepository paiementRepository;

    public List<Paiement> retrieveAllPaiements() {
        return paiementRepository.findAll();
    }

    public Paiement retrievePaiement(Long paiementId) {
        return paiementRepository.findById(paiementId)
                .orElseThrow(() -> new RessourceIntrouvableException("Paiement", paiementId));
    }

    public Paiement addPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    public void removePaiement(Long paiementId) {
        paiementRepository.deleteById(paiementId);
    }

    public Paiement modifyPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }
}
