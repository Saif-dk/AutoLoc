package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.exception.RessourceIntrouvableException;
import tn.esprit.autoloc.repository.EmployeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    EmployeRepository employeRepository;

    public List<Employe> retrieveAllEmployes() {
        return employeRepository.findAll();
    }

    public Employe retrieveEmploye(Long employeId) {
        return employeRepository.findById(employeId)
                .orElseThrow(() -> new RessourceIntrouvableException("Employe", employeId));
    }

    public Employe addEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    public void removeEmploye(Long employeId) {
        employeRepository.deleteById(employeId);
    }

    public Employe modifyEmploye(Employe employe) {
        return employeRepository.save(employe);
    }
}
