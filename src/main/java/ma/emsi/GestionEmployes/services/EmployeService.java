package ma.emsi.GestionEmployes.services;

import ma.emsi.GestionEmployes.repositories.DepartementRepository;
import ma.emsi.GestionEmployes.repositories.ProjetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ma.emsi.GestionEmployes.entities.Employe;
import ma.emsi.GestionEmployes.repositories.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
@Transactional
public class EmployeService {

        private final EmployeRepository employeRepository;
        private final DepartementRepository departementRepository;
        private final ProjetRepository projetRepository;

        @Autowired
        public EmployeService(EmployeRepository employeRepository, DepartementRepository departementRepository, ProjetRepository projetRepository) {
            this.employeRepository = employeRepository;
            this.departementRepository = departementRepository;
            this.projetRepository = projetRepository;
        }

        public Page<Employe> getAllEmployes(int page, int size, String searchName) {
            return employeRepository.findByFullNameContains(searchName, PageRequest.of(page, size));
        }

        public Employe getEmploye(Integer id) {
            return employeRepository.findById(id).orElse(null);
        }

        public Employe saveEmploye(Employe employe) {
            return employeRepository.save(employe);
        }

        public Employe deleteEmploye(Integer id) {
            employeRepository.deleteById(id);
            return null;
        }


}

