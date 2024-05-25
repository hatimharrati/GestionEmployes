package ma.emsi.GestionEmployes.web;

import ma.emsi.GestionEmployes.entities.Projet;
import ma.emsi.GestionEmployes.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projets")
public class ProjetRestController {

    private final ProjetRepository projetRepository;

    @Autowired
    public ProjetRestController(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    @GetMapping
    public Page<Projet> getAllProjets(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "3") int size,
            @RequestParam(name = "search", defaultValue = "") String searchName) {

        return projetRepository.findByDescriptionContaining(searchName, PageRequest.of(page, size));
    }

    @PostMapping
    public Projet saveProjet(@RequestBody Projet projet) {
        return projetRepository.save(projet);
    }

    @PutMapping("update/{id}")
    public Projet updateProjet(@PathVariable int id, @RequestBody Projet projet) {
        projet.setId(id);
        return projetRepository.save(projet);
    }

    @DeleteMapping("delete/{id}")
    public void deleteProjet(@PathVariable int id) {
        projetRepository.deleteById(id);
    }
}
