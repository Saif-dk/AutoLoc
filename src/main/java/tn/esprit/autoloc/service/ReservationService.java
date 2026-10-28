package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.exception.RessourceIntrouvableException;
import tn.esprit.autoloc.repository.ReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService implements ReservationInterface {

    private final ReservationRepository reservationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> trouverTous() {
        return reservationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Reservation trouverParId(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Reservation", id));
    }

    @Override
    public Reservation enregistrer(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation modifier(Long id, Reservation reservation) {
        Reservation existant = trouverParId(id);
        existant.setDateDebut(reservation.getDateDebut());
        existant.setDateFin(reservation.getDateFin());
        existant.setStatut(reservation.getStatut());
        return reservationRepository.save(existant);
    }

    @Override
    public void supprimer(Long id) {
        Reservation existant = trouverParId(id);
        reservationRepository.delete(existant);
    }
}
