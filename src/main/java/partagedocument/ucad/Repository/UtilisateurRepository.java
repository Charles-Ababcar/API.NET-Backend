package partagedocument.ucad.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import partagedocument.ucad.Models.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByLoginAndPwd(String login,String pwd);
   // public Utilisateur findByLogins(String login);
}
