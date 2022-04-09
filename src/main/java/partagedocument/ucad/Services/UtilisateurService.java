package partagedocument.ucad.Services;

import partagedocument.ucad.Models.Utilisateur;

public interface UtilisateurService {
    Utilisateur findByLoginAndPassword(String login,String password);
}
