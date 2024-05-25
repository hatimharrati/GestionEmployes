package ma.emsi.GestionEmployes.repositories;

import jakarta.transaction.Transactional;
import ma.emsi.GestionEmployes.entities.Departement;
import ma.emsi.GestionEmployes.entities.Employe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DepartementRepository extends JpaRepository<Departement, Integer> {
    Page<Departement> findByNomContaining(String nom, Pageable pageable);
}
