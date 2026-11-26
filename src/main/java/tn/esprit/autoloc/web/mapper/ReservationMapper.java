package tn.esprit.autoloc.web.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.service.IClientService;
import tn.esprit.autoloc.service.IVehiculeService;
import tn.esprit.autoloc.web.dto.ReservationDTO;

@Component
@AllArgsConstructor
public class ReservationMapper {

    IClientService clientService;
    IVehiculeService vehiculeService;

    public ReservationDTO toDto(Reservation reservation) {
        Long clientId = reservation.getClient() != null ? reservation.getClient().getIdClient() : null;
        Long vehiculeId = reservation.getVehicule() != null ? reservation.getVehicule().getIdVehicule() : null;
        return new ReservationDTO(
                reservation.getIdReservation(),
                reservation.getDateDebut(),
                reservation.getDateFin(),
                reservation.getStatut(),
                clientId,
                vehiculeId);
    }

    public Reservation toEntity(ReservationDTO dto) {
        if (dto.dateFin().isBefore(dto.dateDebut())) {
            throw new IllegalArgumentException("La date de fin ne peut pas être antérieure à la date de début");
        }
        Reservation reservation = new Reservation();
        reservation.setDateDebut(dto.dateDebut());
        reservation.setDateFin(dto.dateFin());
        reservation.setStatut(dto.statut() != null ? dto.statut() : StatutReservation.EN_ATTENTE);
        reservation.setClient(clientService.retrieveClient(dto.clientId()));          // 404 si inexistant
        reservation.setVehicule(vehiculeService.retrieveVehicule(dto.vehiculeId()));  // 404 si inexistant
        return reservation;
    }
}
