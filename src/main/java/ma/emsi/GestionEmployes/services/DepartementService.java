package ma.emsi.GestionEmployes.services;

import ma.emsi.GestionEmployes.entities.Departement;
import ma.emsi.GestionEmployes.repositories.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;

@Service
@Transactional
public class DepartementService {

    private final DepartementRepository departementRepository;

    @Autowired
    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public Page<Departement> getAllDepartements(int page, int size, String searchName) {
        return departementRepository.findByNomContaining(searchName, PageRequest.of(page, size));
    }

    public Departement getDepartementById(int id) {
        return departementRepository.findById(id).orElse(null);
    }

    public Departement saveDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    public void deleteDepartementById(int id) {
        departementRepository.deleteById(id);
    }
}
