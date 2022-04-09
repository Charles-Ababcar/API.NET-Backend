package partagedocument.ucad.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import partagedocument.ucad.Models.Rendezvous;
import partagedocument.ucad.Models.Structure;
import partagedocument.ucad.Models.Utilisateur;

import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<Rendezvous,Long> {

  @Query(value = "select  c from Rendezvous c,Structure u "+ "where c.structure.idStructure=:id and c.utilisateur.id=u.idStructure")
    List<Rendezvous> getRendezvousBy(@Param("id") Long id);
 // List<Rendezvous> allRv(@RequestBody Structure structure);

}
