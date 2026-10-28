package tn.esprit.autoloc.service;
import tn.esprit.autoloc.domain.Agence;
import java.util.List;
public interface AgenceInterface {

    public List<Agence> retrieveAllAgence();
    public Agence retrieveAgence(Long pagenceId);
    public Agence addAgence(Agence c);
    public void removeAgence(Long AgenceId);
    public Agence modifyAgence(Agence projet);
}