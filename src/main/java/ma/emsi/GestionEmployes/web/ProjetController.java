package ma.emsi.GestionEmployes.web;

import ma.emsi.GestionEmployes.entities.Projet;
import ma.emsi.GestionEmployes.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;

@Controller
public class ProjetController {

    @Autowired
    private ProjetRepository projetRepository;

    @GetMapping("/projets")
    public String listeProjets(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "3") int size,
                               @RequestParam(name="search", defaultValue = "") String searchName){

        Page<Projet> pageProjets = projetRepository.findByDescriptionContaining(searchName, PageRequest.of(page, size));

        int[] pages = new int[pageProjets.getTotalPages()];
        for(int i = 0; i < pages.length; i++)
            pages[i] = i;

        model.addAttribute("projets", pageProjets.getContent());
        model.addAttribute("pages", pages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", searchName);
        return "projets";
    }

    @GetMapping("/projet/add")
    public String addProjet(Model model) {
        model.addAttribute("projet", new Projet());
        return "formAddProjet";
    }

    @PostMapping("/projet/save")
    public String saveProjet(@ModelAttribute Projet projet) {
        projetRepository.save(projet);
        return "redirect:/projets";
    }

    @PostMapping("/projet/update/{id}")
    public String updateProjet(@PathVariable int id, @ModelAttribute Projet projet) {
        projet.setId(id);
        projetRepository.save(projet);
        return "redirect:/projets";
    }
    
    @GetMapping("/projet/edit/{id}")
    public String editProjet(@PathVariable int id, Model model) {
        Projet projet = projetRepository.findById(id).orElse(null);
        model.addAttribute("projet", projet);
        return "formEditProjet";
    }


    @GetMapping("/projet/delete/{id}")
    public String deleteProjet(@PathVariable int id) {
        projetRepository.deleteById(id);
        return "redirect:/projets";
    }
}
