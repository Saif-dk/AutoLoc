package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.exception.RessourceIntrouvableException;
import tn.esprit.autoloc.repository.MaintenanceRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {

    MaintenanceRepository maintenanceRepository;

    public List<Maintenance> retrieveAllMaintenances() {
        return maintenanceRepository.findAll();
    }

    public Maintenance retrieveMaintenance(Long maintenanceId) {
        return maintenanceRepository.findById(maintenanceId)
                .orElseThrow(() -> new RessourceIntrouvableException("Maintenance", maintenanceId));
    }

    public Maintenance addMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    public void removeMaintenance(Long maintenanceId) {
        maintenanceRepository.deleteById(maintenanceId);
    }

    public Maintenance modifyMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }
}
