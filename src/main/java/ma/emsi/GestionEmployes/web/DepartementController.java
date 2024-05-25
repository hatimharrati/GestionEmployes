package ma.emsi.GestionEmployes.web;

import ma.emsi.GestionEmployes.entities.Departement;
import ma.emsi.GestionEmployes.repositories.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DepartementController {

    @Autowired
    private DepartementRepository departementRepository;

    @GetMapping("/departements")
    public String listDepartements(Model model,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "3") int size,
                                   @RequestParam(name = "search", defaultValue = "") String searchName) {

        Page<Departement> pageDepartements = departementRepository.findByNomContaining(searchName, PageRequest.of(page, size));

        int[] pages = new int[pageDepartements.getTotalPages()];
        for (int i = 0; i < pages.length; i++)
            pages[i] = i;

        model.addAttribute("departements", pageDepartements.getContent());
        model.addAttribute("pages", pages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", searchName);

        return "departements";
    }

    @GetMapping("/department/add")
    public String addDepartementForm(Model model) {
        model.addAttribute("departement", new Departement());
        return "formAddDepartement";
    }

    @PostMapping("/departements/save")
    public String saveDepartement(@ModelAttribute Departement departement) {
        departementRepository.save(departement);
        return "redirect:/departements";
    }

    @GetMapping("/departements/edit/{id}")
    public String editDepartementForm(@PathVariable int id, Model model) {
        Departement departement = departementRepository.findById(id).orElse(null);
        model.addAttribute("departement", departement);
        return "formEditDepartement";
    }

    @PostMapping("/departements/update/{id}")
    public String updateDepartement(@PathVariable int id, @ModelAttribute Departement departement) {
        departement.setId(id);
        departementRepository.save(departement);
        return "redirect:/departements";
    }

    @GetMapping("/departements/delete/{id}")
    public String deleteDepartement(@PathVariable int id) {
        departementRepository.deleteById(id);
        return "redirect:/departements";
    }
}
