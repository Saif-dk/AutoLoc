package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;
import tn.esprit.autoloc.web.dto.MaintenanceDTO;
import tn.esprit.autoloc.web.mapper.MaintenanceMapper;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/maintenance")
public class MaintenanceRestController {

    IMaintenanceService maintenanceService;
    MaintenanceMapper maintenanceMapper;

    // http://localhost:8080/maintenance/retrieve-all-maintenances
    @GetMapping("/retrieve-all-maintenances")
    public List<MaintenanceDTO> getMaintenances() {
        return maintenanceService.retrieveAllMaintenances().stream()
                .map(maintenanceMapper::toDto)
                .toList();
    }

    // http://localhost:8080/maintenance/retrieve-maintenance/1
    @GetMapping("/retrieve-maintenance/{maintenance-id}")
    public MaintenanceDTO retrieveMaintenance(@PathVariable("maintenance-id") Long maintenanceId) {
        return maintenanceMapper.toDto(maintenanceService.retrieveMaintenance(maintenanceId));
    }

    // http://localhost:8080/maintenance/add-maintenance
    @PostMapping("/add-maintenance")
    public MaintenanceDTO addMaintenance(@Valid @RequestBody MaintenanceDTO dto) {
        return maintenanceMapper.toDto(maintenanceService.addMaintenance(maintenanceMapper.toEntity(dto)));
    }

    // http://localhost:8080/maintenance/remove-maintenance/1
    @DeleteMapping("/remove-maintenance/{maintenance-id}")
    public void removeMaintenance(@PathVariable("maintenance-id") Long maintenanceId) {
        maintenanceService.removeMaintenance(maintenanceId);
    }

    // http://localhost:8080/maintenance/modify-maintenance
    @PutMapping("/modify-maintenance")
    public MaintenanceDTO modifyMaintenance(@Valid @RequestBody MaintenanceDTO dto) {
        if (dto.idMaintenance() == null) {
            throw new IllegalArgumentException("idMaintenance est obligatoire pour la modification");
        }
        maintenanceService.retrieveMaintenance(dto.idMaintenance());          // 404 si l'élément n'existe pas
        Maintenance entity = maintenanceMapper.toEntity(dto);
        entity.setIdMaintenance(dto.idMaintenance());
        return maintenanceMapper.toDto(maintenanceService.modifyMaintenance(entity));
    }
}
