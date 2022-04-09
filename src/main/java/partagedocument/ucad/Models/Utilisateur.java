package partagedocument.ucad.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
   // private boolean role;
    private String login;
    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur")
    private List<Rendezvous> rendezvous;
    private String pwd;

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
             //   ", role=" + role +
                ", login='" + login + '\'' +
                ", rendezvous=" + rendezvous +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
