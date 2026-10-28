package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.exception.RessourceIntrouvableException;
import tn.esprit.autoloc.repository.EquipementRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EquipementServiceImpl implements IEquipementService {

    EquipementRepository equipementRepository;

    public List<Equipement> retrieveAllEquipements() {
        return equipementRepository.findAll();
    }

    public Equipement retrieveEquipement(Long equipementId) {
        return equipementRepository.findById(equipementId)
                .orElseThrow(() -> new RessourceIntrouvableException("Equipement", equipementId));
    }

    public Equipement addEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    public void removeEquipement(Long equipementId) {
        equipementRepository.deleteById(equipementId);
    }

    public Equipement modifyEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }
}
