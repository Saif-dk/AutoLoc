package tn.esprit.autoloc.web.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IContratService;
import tn.esprit.autoloc.web.dto.PaiementDTO;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class PaiementMapper {

    IContratService contratService;

    public PaiementDTO toDto(Paiement paiement) {
        Long contratId = paiement.getContrat() != null ? paiement.getContrat().getIdContrat() : null;
        return new PaiementDTO(
                paiement.getIdPaiement(),
                paiement.getMontant(),
                paiement.getDatePaiement(),
                paiement.getModePaiement(),
                contratId);
    }

    public Paiement toEntity(PaiementDTO dto) {
        Paiement paiement = new Paiement();
        paiement.setMontant(dto.montant());
        paiement.setDatePaiement(dto.datePaiement() != null ? dto.datePaiement() : LocalDate.now());
        paiement.setModePaiement(dto.modePaiement());
        paiement.setContrat(contratService.retrieveContrat(dto.contratId()));   // 404 si inexistant
        return paiement;
    }
}
