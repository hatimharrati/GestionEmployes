package ma.emsi.GestionEmployes;

import ma.emsi.GestionEmployes.repositories.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionEmployesApplication implements CommandLineRunner {

    @Autowired
    EmployeRepository employeRepository;
    public static void main(String[] args) {
        SpringApplication.run(GestionEmployesApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {


       //List<Employe> students = employeRepository.findAll();
        // students.forEach(System.out::println);
        //List<Employe> pageStudents = employeRepository.findByFullNameContains("Émilie Giroux");
        //pageStudents.forEach(System.out::println);
    }
}
