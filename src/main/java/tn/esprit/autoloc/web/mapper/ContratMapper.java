package tn.esprit.autoloc.web.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IReservationService;
import tn.esprit.autoloc.web.dto.ContratDTO;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class ContratMapper {

    IReservationService reservationService;

    public ContratDTO toDto(Contrat contrat) {
        Long reservationId = contrat.getReservation() != null ? contrat.getReservation().getIdReservation() : null;
        return new ContratDTO(
                contrat.getIdContrat(),
                contrat.getDateSignature(),
                contrat.getMontantTotal(),
                contrat.isValide(),
                reservationId);
    }

    public Contrat toEntity(ContratDTO dto) {
        Contrat contrat = new Contrat();
        contrat.setDateSignature(dto.dateSignature() != null ? dto.dateSignature() : LocalDate.now());
        contrat.setMontantTotal(dto.montantTotal());
        contrat.setValide(dto.valide());
        contrat.setReservation(reservationService.retrieveReservation(dto.reservationId()));   // 404 si inexistante
        return contrat;
    }
}
