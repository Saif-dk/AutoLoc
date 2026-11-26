package tn.esprit.autoloc.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GestionnaireExceptions {

    // 404 : ressource introuvable (Agence, Vehicule, Client, etc.)
    @ExceptionHandler(RessourceIntrouvableException.class)
    public ResponseEntity<ErreurReponse> handleIntrouvable(RessourceIntrouvableException ex) {
        return reponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // 400 : données invalides (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErreurReponse> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + " : " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return reponse(HttpStatus.BAD_REQUEST, message);
    }

    // 400 : JSON mal formé ou valeur incorrecte (enum inconnu, date invalide...)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErreurReponse> handleCorpsIllisible(HttpMessageNotReadableException ex) {
        return reponse(HttpStatus.BAD_REQUEST,
                "Corps de la requête invalide (JSON mal formé ou valeur incorrecte)");
    }

    // 400 : règle de gestion non respectée (id manquant, dates incohérentes...)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErreurReponse> handleArgumentInvalide(IllegalArgumentException ex) {
        return reponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // 409 : contrainte de base de données (doublon d'immatriculation, élément encore référencé...)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErreurReponse> handleIntegrite(DataIntegrityViolationException ex) {
        return reponse(HttpStatus.CONFLICT,
                "Opération refusée : doublon ou élément encore référencé par d'autres données");
    }

    // 500 : toute autre erreur imprévue (les erreurs HTTP standard de Spring gardent leur statut)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErreurReponse> handleGenerale(Exception ex) {
        if (ex instanceof ErrorResponse erreurSpring) {
            HttpStatus status = HttpStatus.valueOf(erreurSpring.getStatusCode().value());
            return reponse(status, status.getReasonPhrase());
        }
        log.error("Erreur interne", ex);
        return reponse(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur interne est survenue");
    }

    private ResponseEntity<ErreurReponse> reponse(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(new ErreurReponse(status.value(), message, LocalDateTime.now()));
    }
}
