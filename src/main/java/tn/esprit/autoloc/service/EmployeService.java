package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.exception.RessourceIntrouvableException;
import tn.esprit.autoloc.repository.EmployeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeService implements EmployeInterface {

    private final EmployeRepository employeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Employe> trouverTous() {
        return employeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Employe trouverParId(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Employe", id));
    }

    @Override
    public Employe enregistrer(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public Employe modifier(Long id, Employe employe) {
        Employe existant = trouverParId(id);
        existant.setNom(employe.getNom());
        existant.setPrenom(employe.getPrenom());
        existant.setRole(employe.getRole());
        return employeRepository.save(existant);
    }

    @Override
    public void supprimer(Long id) {
        Employe existant = trouverParId(id);
        employeRepository.delete(existant);
    }
}
