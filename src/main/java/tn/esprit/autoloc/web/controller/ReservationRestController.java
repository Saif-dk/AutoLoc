package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.IReservationService;
import tn.esprit.autoloc.web.dto.ReservationDTO;
import tn.esprit.autoloc.web.mapper.ReservationMapper;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationRestController {

    IReservationService reservationService;
    ReservationMapper reservationMapper;

    // http://localhost:8080/reservation/retrieve-all-reservations
    @GetMapping("/retrieve-all-reservations")
    public List<ReservationDTO> getReservations() {
        return reservationService.retrieveAllReservations().stream()
                .map(reservationMapper::toDto)
                .toList();
    }

    // http://localhost:8080/reservation/retrieve-reservation/1
    @GetMapping("/retrieve-reservation/{reservation-id}")
    public ReservationDTO retrieveReservation(@PathVariable("reservation-id") Long reservationId) {
        return reservationMapper.toDto(reservationService.retrieveReservation(reservationId));
    }

    // http://localhost:8080/reservation/add-reservation
    @PostMapping("/add-reservation")
    public ReservationDTO addReservation(@Valid @RequestBody ReservationDTO dto) {
        return reservationMapper.toDto(reservationService.addReservation(reservationMapper.toEntity(dto)));
    }

    // http://localhost:8080/reservation/remove-reservation/1
    @DeleteMapping("/remove-reservation/{reservation-id}")
    public void removeReservation(@PathVariable("reservation-id") Long reservationId) {
        reservationService.removeReservation(reservationId);
    }

    // http://localhost:8080/reservation/modify-reservation
    @PutMapping("/modify-reservation")
    public ReservationDTO modifyReservation(@Valid @RequestBody ReservationDTO dto) {
        if (dto.idReservation() == null) {
            throw new IllegalArgumentException("idReservation est obligatoire pour la modification");
        }
        reservationService.retrieveReservation(dto.idReservation());          // 404 si l'élément n'existe pas
        Reservation entity = reservationMapper.toEntity(dto);
        entity.setIdReservation(dto.idReservation());
        return reservationMapper.toDto(reservationService.modifyReservation(entity));
    }
}
