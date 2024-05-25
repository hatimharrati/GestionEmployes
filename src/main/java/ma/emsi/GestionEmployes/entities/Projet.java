package ma.emsi.GestionEmployes.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "TABLE_PROJET")
public class Projet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date debutProj;
    @Temporal(TemporalType.DATE)
    private Date finProj;
    @ManyToMany // Many projects can have many employees
    @JoinTable(name = "EMPLOYE_PROJET",
            joinColumns = @JoinColumn(name = "projet_id"),
            inverseJoinColumns = @JoinColumn(name = "employe_id"))
    private List<Employe> employe;
}

