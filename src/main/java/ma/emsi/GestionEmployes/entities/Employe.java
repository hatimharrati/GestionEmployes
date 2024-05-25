package ma.emsi.GestionEmployes.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data @NoArgsConstructor // lombok

@Entity @Table(name = "TABLE_EMPLOYE")
public class Employe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "registration_N", length = 30, unique = true)
    private String registrationNumber;
    @Column(name = "name", nullable = false)
    private String fullName;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @ManyToOne
    private Departement departement;
    @ManyToOne
    private Projet projet;

    public Employe(Integer id, String registrationNumber, String fullName, Date birthday, Departement departement, Projet projet) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.fullName = fullName;
        this.birthday = birthday;
        this.departement = departement;
        this.projet = projet;

    }

}
