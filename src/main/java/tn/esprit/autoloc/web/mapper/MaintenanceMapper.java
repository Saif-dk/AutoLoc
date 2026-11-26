package tn.esprit.autoloc.web.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IVehiculeService;
import tn.esprit.autoloc.web.dto.MaintenanceDTO;

@Component
@AllArgsConstructor
public class MaintenanceMapper {

    IVehiculeService vehiculeService;

    public MaintenanceDTO toDto(Maintenance maintenance) {
        Long vehiculeId = maintenance.getVehicule() != null ? maintenance.getVehicule().getIdVehicule() : null;
        return new MaintenanceDTO(
                maintenance.getIdMaintenance(),
                maintenance.getDateDebut(),
                maintenance.getDateFin(),
                maintenance.getDescription(),
                vehiculeId);
    }

    public Maintenance toEntity(MaintenanceDTO dto) {
        if (dto.dateFin() != null && dto.dateFin().isBefore(dto.dateDebut())) {
            throw new IllegalArgumentException("La date de fin ne peut pas être antérieure à la date de début");
        }
        Maintenance maintenance = new Maintenance();
        maintenance.setDateDebut(dto.dateDebut());
        maintenance.setDateFin(dto.dateFin());
        maintenance.setDescription(dto.description());
        maintenance.setVehicule(vehiculeService.retrieveVehicule(dto.vehiculeId()));   // 404 si inexistant
        return maintenance;
    }
}
