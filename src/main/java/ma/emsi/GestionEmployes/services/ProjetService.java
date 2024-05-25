package ma.emsi.GestionEmployes.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ma.emsi.GestionEmployes.entities.Projet;
import ma.emsi.GestionEmployes.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
@Transactional

public class ProjetService {

    private final ProjetRepository projetRepository;

    @Autowired
    public ProjetService(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    public Page<Projet> getAllProjets(int page, int size, String searchName) {
        return projetRepository.findByDescriptionContaining(searchName, PageRequest.of(page, size));
    }

    public Projet getProjetById(int id) {
        return projetRepository.findById(id).orElse(null);
    }

    public Projet saveProjet(Projet projet) {
        return projetRepository.save(projet);
    }

    public void deleteProjetById(int id) {
        projetRepository.deleteById(id);
    }
}
