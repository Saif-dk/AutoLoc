package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;

import java.util.List;

public interface IEmployeService {

    List<Employe> retrieveAllEmployes();

    Employe retrieveEmploye(Long employeId);

    Employe addEmploye(Employe employe);

    void removeEmploye(Long employeId);

    Employe modifyEmploye(Employe employe);
}
