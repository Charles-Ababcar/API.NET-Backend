package partagedocument.ucad.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Structure {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
    @SequenceGenerator(name = "users_seq_gen", sequenceName = "users_id_seq")
    private long idStructure;
   private String nom;
    private String adresse;
    private String contact;
    private String capacite;
    private Long nbresplaces;
    private Long nombredoses;
    @JsonIgnore
    @OneToMany(mappedBy = "structure")
    private List<Rendezvous> rendezvous;

    @Override
    public String toString() {
        return "Structure{" +
                "idStructure=" + idStructure +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", contact='" + contact + '\'' +
                ", capacite='" + capacite + '\'' +
                ", nbresplaces=" + nbresplaces +
                ", nombredoses=" + nombredoses +
                ", rendezvous=" + rendezvous +
                '}';
    }
}
