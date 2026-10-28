package tn.esprit.autoloc.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/employe")
public class EmployeRestController {

    IEmployeService employeService;

    // http://localhost:8080/employe/retrieve-all-employes
    @GetMapping("/retrieve-all-employes")
    public List<Employe> getEmployes() {
        return employeService.retrieveAllEmployes();
    }

    // http://localhost:8080/employe/retrieve-employe/1
    @GetMapping("/retrieve-employe/{employe-id}")
    public Employe retrieveEmploye(@PathVariable("employe-id") Long employeId) {
        return employeService.retrieveEmploye(employeId);
    }

    // http://localhost:8080/employe/add-employe
    @PostMapping("/add-employe")
    public Employe addEmploye(@RequestBody Employe employe) {
        return employeService.addEmploye(employe);
    }

    // http://localhost:8080/employe/remove-employe/1
    @DeleteMapping("/remove-employe/{employe-id}")
    public void removeEmploye(@PathVariable("employe-id") Long employeId) {
        employeService.removeEmploye(employeId);
    }

    // http://localhost:8080/employe/modify-employe
    @PutMapping("/modify-employe")
    public Employe modifyEmploye(@RequestBody Employe employe) {
        return employeService.modifyEmploye(employe);
    }
}
