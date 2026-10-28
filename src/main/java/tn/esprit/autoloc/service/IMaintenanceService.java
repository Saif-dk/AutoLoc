package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {

    List<Maintenance> retrieveAllMaintenances();

    Maintenance retrieveMaintenance(Long maintenanceId);

    Maintenance addMaintenance(Maintenance maintenance);

    void removeMaintenance(Long maintenanceId);

    Maintenance modifyMaintenance(Maintenance maintenance);
}
