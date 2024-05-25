package ma.emsi.GestionEmployes.web;

import ma.emsi.GestionEmployes.entities.Employe;
import ma.emsi.GestionEmployes.repositories.DepartementRepository;
import ma.emsi.GestionEmployes.repositories.EmployeRepository;
import ma.emsi.GestionEmployes.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employes")
public class EmployeRestController {

    private final EmployeRepository employeRepository;
    private final DepartementRepository departementRepository;
    private final ProjetRepository projetRepository;

    @Autowired
    public EmployeRestController(EmployeRepository employeRepository, DepartementRepository departementRepository, ProjetRepository projetRepository) {
        this.employeRepository = employeRepository;
        this.departementRepository = departementRepository;
        this.projetRepository = projetRepository;
    }

    @GetMapping
    public Page<Employe> getAllEmployes(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "search", defaultValue = "") String searchName) {

        return employeRepository.findByFullNameContains(searchName, PageRequest.of(page, size));
    }

    @PostMapping
    public Employe saveEmploye(@RequestBody Employe employe) {
        return employeRepository.save(employe);
    }

    @PutMapping("update/{id}")
    public Employe updateEmploye(@PathVariable Integer id, @RequestBody Employe employe) {
        employe.setId(id);
        return employeRepository.save(employe);
    }

    @DeleteMapping("delete/{id}")
    public void deleteEmploye(@PathVariable Integer id) {
        employeRepository.deleteById(id);
    }
}
