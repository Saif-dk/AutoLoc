package tn.esprit.autoloc.exception;

public class RessourceIntrouvableException extends RuntimeException {

    private final String entite;
    private final Object id;

    public RessourceIntrouvableException(String entite, Object id) {
        super(entite + " introuvable avec l'id " + id);
        this.entite = entite;
        this.id = id;
    }

    public String getEntite() {
        return entite;
    }

    public Object getId() {
        return id;
    }
}
