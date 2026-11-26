package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IEmployeService;
import tn.esprit.autoloc.web.dto.EmployeDTO;
import tn.esprit.autoloc.web.mapper.EmployeMapper;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/employe")
public class EmployeRestController {

    IEmployeService employeService;
    EmployeMapper employeMapper;

    // http://localhost:8080/employe/retrieve-all-employes
    @GetMapping("/retrieve-all-employes")
    public List<EmployeDTO> getEmployes() {
        return employeService.retrieveAllEmployes().stream()
                .map(employeMapper::toDto)
                .toList();
    }

    // http://localhost:8080/employe/retrieve-employe/1
    @GetMapping("/retrieve-employe/{employe-id}")
    public EmployeDTO retrieveEmploye(@PathVariable("employe-id") Long employeId) {
        return employeMapper.toDto(employeService.retrieveEmploye(employeId));
    }

    // http://localhost:8080/employe/add-employe
    @PostMapping("/add-employe")
    public EmployeDTO addEmploye(@Valid @RequestBody EmployeDTO dto) {
        return employeMapper.toDto(employeService.addEmploye(employeMapper.toEntity(dto)));
    }

    // http://localhost:8080/employe/remove-employe/1
    @DeleteMapping("/remove-employe/{employe-id}")
    public void removeEmploye(@PathVariable("employe-id") Long employeId) {
        employeService.removeEmploye(employeId);
    }

    // http://localhost:8080/employe/modify-employe
    @PutMapping("/modify-employe")
    public EmployeDTO modifyEmploye(@Valid @RequestBody EmployeDTO dto) {
        if (dto.idEmploye() == null) {
            throw new IllegalArgumentException("idEmploye est obligatoire pour la modification");
        }
        employeService.retrieveEmploye(dto.idEmploye());          // 404 si l'élément n'existe pas
        Employe entity = employeMapper.toEntity(dto);
        entity.setIdEmploye(dto.idEmploye());
        return employeMapper.toDto(employeService.modifyEmploye(entity));
    }
}
