package ma.emsi.GestionEmployes.web;

import ma.emsi.GestionEmployes.entities.Employe;
import ma.emsi.GestionEmployes.repositories.DepartementRepository;
import ma.emsi.GestionEmployes.repositories.EmployeRepository;
import ma.emsi.GestionEmployes.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller public class EmployeController {
    private final EmployeRepository employeRepository;
    private final DepartementRepository departementRepository;
    private final ProjetRepository projetRepository;

    @Autowired
    public EmployeController(EmployeRepository employeRepository, DepartementRepository departementRepository, ProjetRepository projetRepository) {
        this.employeRepository = employeRepository;
        this.departementRepository = departementRepository;
        this.projetRepository = projetRepository;
    }

    @GetMapping(path="/index")
    public String allEmployes(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size,
                              @RequestParam(name="search", defaultValue = "") String searchName)
    {

        //Page<Employe> pageEmployes = employeRepository.findAll(PageRequest.of(page,size));

        Page<Employe> pageEmployes = employeRepository.findByFullNameContains(searchName, PageRequest.of(page,size));

        int[] pages = new int[pageEmployes.getTotalPages()];
        for(int i=0; i<pages.length; i++)
            pages[i]=i;


        model.addAttribute("pageEmployes",pageEmployes.getContent());
        model.addAttribute("tabPages", pages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", searchName);
        return "employes";
    }

    @GetMapping(path = "/")
    public String homePage(){
        return "/home";
    }

    @GetMapping(path="/create")
    public String createEmploye(Model model){
        Employe employe = new Employe();
        model.addAttribute("employe", employe);
        model.addAttribute("departements", departementRepository.findAll());
        model.addAttribute("projets", projetRepository.findAll());
        return "formAddEmploye";

    }

    @PostMapping(path = "/save")
    public String saveEmploye(Model model, Employe e,
                              @RequestParam(name="currentPage", defaultValue = "0") int page,
                              @RequestParam(name="size", defaultValue = "10") int size,
                              @RequestParam(name="searchName", defaultValue = "") String search){
        employeRepository.save(e);
        return "redirect:/index?page="+page+"&size="+size+"&search="+search;
    }

    @GetMapping(path = "/edit")
    public String editEmploye(Model model , int page, int size, String search, Integer id){
        Employe employe = employeRepository.findById(id).orElse(null);
        model.addAttribute("departements", departementRepository.findAll());
        model.addAttribute("projets", projetRepository.findAll());
        if(employe == null) throw new RuntimeException("Erreur");
        model.addAttribute("employe", employe);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", search);
        return "formEditEmploye";
    }


    @GetMapping(path="/delete")
    public String deleteEmploye(
            int page, int size, String search,
            @RequestParam(name="id") Integer id)
    {
        employeRepository.deleteById(id);
        return "redirect:/index?page="+page+"&size="+size+"&search="+search;
    }

}