package ma.emsi.GestionEmployes.repositories;

import jakarta.transaction.Transactional;
import ma.emsi.GestionEmployes.entities.Employe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional public interface EmployeRepository extends JpaRepository<Employe,Integer> {

List<Employe> findByFullName(String name);
List<Employe> findByFullNameContains(String name);
Page<Employe> findByFullNameContains(String name, PageRequest pageable);

}
