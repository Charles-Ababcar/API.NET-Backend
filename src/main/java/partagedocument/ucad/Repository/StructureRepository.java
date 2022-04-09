package partagedocument.ucad.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import partagedocument.ucad.Models.Structure;

import java.util.List;

@Repository
public interface StructureRepository extends JpaRepository<Structure,Long> {
    List<Structure> findByNomContains(@Param("nom") String nom);
}
