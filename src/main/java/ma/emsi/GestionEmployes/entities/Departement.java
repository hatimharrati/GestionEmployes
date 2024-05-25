package ma.emsi.GestionEmployes.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "TABLE_DEPARTEMENT")
public class Departement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    @OneToMany
    Collection<Employe> employees;




}

