package tn.esprit.autoloc.exception;

import java.time.LocalDateTime;

public record ErreurReponse(
        int status,
        String message,
        LocalDateTime date) {
}
