package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.exception.RessourceIntrouvableException;
import tn.esprit.autoloc.repository.MaintenanceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MaintenanceService implements MaintenanceInterface {

    private final MaintenanceRepository maintenanceRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Maintenance> trouverTous() {
        return maintenanceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Maintenance trouverParId(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Maintenance", id));
    }

    @Override
    public Maintenance enregistrer(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance modifier(Long id, Maintenance maintenance) {
        Maintenance existant = trouverParId(id);
        existant.setDateDebut(maintenance.getDateDebut());
        existant.setDateFin(maintenance.getDateFin());
        existant.setDescription(maintenance.getDescription());
        return maintenanceRepository.save(existant);
    }

    @Override
    public void supprimer(Long id) {
        Maintenance existant = trouverParId(id);
        maintenanceRepository.delete(existant);
    }
}
