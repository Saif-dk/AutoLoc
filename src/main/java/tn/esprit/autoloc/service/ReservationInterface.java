package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Reservation;

import java.util.List;

public interface ReservationInterface {

    List<Reservation> trouverTous();

    Reservation trouverParId(Long id);

    Reservation enregistrer(Reservation reservation);

    Reservation modifier(Long id, Reservation reservation);

    void supprimer(Long id);
}
