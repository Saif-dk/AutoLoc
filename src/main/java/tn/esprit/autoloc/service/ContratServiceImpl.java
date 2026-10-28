package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.exception.RessourceIntrouvableException;
import tn.esprit.autoloc.repository.ContratRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ContratServiceImpl implements IContratService {

    ContratRepository contratRepository;

    public List<Contrat> retrieveAllContrats() {
        return contratRepository.findAll();
    }

    public Contrat retrieveContrat(Long contratId) {
        return contratRepository.findById(contratId)
                .orElseThrow(() -> new RessourceIntrouvableException("Contrat", contratId));
    }

    public Contrat addContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    public void removeContrat(Long contratId) {
        contratRepository.deleteById(contratId);
    }

    // On recharge le contrat existant : un save() direct avec une liste de paiements vide
    // supprimerait tous les paiements à cause de orphanRemoval = true.
    public Contrat modifyContrat(Contrat contrat) {
        Contrat existant = retrieveContrat(contrat.getIdContrat());
        existant.setDateSignature(contrat.getDateSignature());
        existant.setMontantTotal(contrat.getMontantTotal());
        existant.setValide(contrat.isValide());
        existant.setReservation(contrat.getReservation());
        return contratRepository.save(existant);
    }
}
