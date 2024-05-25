package ma.emsi.GestionEmployes.repositories;

import jakarta.transaction.Transactional;
import ma.emsi.GestionEmployes.entities.Employe;
import ma.emsi.GestionEmployes.entities.Projet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetRepository extends JpaRepository<Projet, Integer> {
    Page<Projet> findByDescriptionContaining(String description, Pageable pageable);
    Projet findByDescription(String description);
}
