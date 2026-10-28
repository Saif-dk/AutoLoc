package tn.esprit.autoloc.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/maintenance")
public class MaintenanceRestController {

    IMaintenanceService maintenanceService;

    // http://localhost:8080/maintenance/retrieve-all-maintenances
    @GetMapping("/retrieve-all-maintenances")
    public List<Maintenance> getMaintenances() {
        return maintenanceService.retrieveAllMaintenances();
    }

    // http://localhost:8080/maintenance/retrieve-maintenance/1
    @GetMapping("/retrieve-maintenance/{maintenance-id}")
    public Maintenance retrieveMaintenance(@PathVariable("maintenance-id") Long maintenanceId) {
        return maintenanceService.retrieveMaintenance(maintenanceId);
    }

    // http://localhost:8080/maintenance/add-maintenance
    @PostMapping("/add-maintenance")
    public Maintenance addMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceService.addMaintenance(maintenance);
    }

    // http://localhost:8080/maintenance/remove-maintenance/1
    @DeleteMapping("/remove-maintenance/{maintenance-id}")
    public void removeMaintenance(@PathVariable("maintenance-id") Long maintenanceId) {
        maintenanceService.removeMaintenance(maintenanceId);
    }

    // http://localhost:8080/maintenance/modify-maintenance
    @PutMapping("/modify-maintenance")
    public Maintenance modifyMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceService.modifyMaintenance(maintenance);
    }
}
