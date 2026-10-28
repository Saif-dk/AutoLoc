package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.exception.RessourceIntrouvableException;
import tn.esprit.autoloc.repository.EquipementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipementService implements EquipementInterface {

    private final EquipementRepository equipementRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Equipement> trouverTous() {
        return equipementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Equipement trouverParId(Long id) {
        return equipementRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Equipement", id));
    }

    @Override
    public Equipement enregistrer(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement modifier(Long id, Equipement equipement) {
        Equipement existant = trouverParId(id);
        existant.setLibelle(equipement.getLibelle());
        return equipementRepository.save(existant);
    }

    @Override
    public void supprimer(Long id) {
        Equipement existant = trouverParId(id);
        equipementRepository.delete(existant);
    }
}
