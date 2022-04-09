package partagedocument.ucad.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import partagedocument.ucad.Models.Utilisateur;
import partagedocument.ucad.Repository.UtilisateurRepository;
import partagedocument.ucad.component.Status;
import partagedocument.ucad.exception.ResourceNotFoundException;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/api")
public class UtilisateurController {
    @Autowired
    UtilisateurRepository repository;

    @GetMapping("/users")
    public List<Utilisateur> getAllUtilisateur() {
        System.out.println("Get all Utilisateur...");

        List<Utilisateur> Utilisateur = new ArrayList<>();
        repository.findAll().forEach(Utilisateur::add);

        return Utilisateur;
    }
    /*@RequestMapping(value = "/sendemail")
    public String sendEmail() {
        return "Email sent successfully";
    }*/
    @GetMapping("/users/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable(value = "id") Long UtilisateurId)
            throws ResourceNotFoundException {
        Utilisateur Utilisateur = repository.findById(UtilisateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found for this id :: " + UtilisateurId));
        return ResponseEntity.ok().body(Utilisateur);
    }

 /* @GetMapping("/rechercheU/{}")
  public Utilisateur recherche_User(@PathVariable String login)
  {
      return repository.findByLogins(login);
  }*/
    @PostMapping("/users/login")
    public Utilisateur getUtilisateurByLogin(@RequestBody Utilisateur utilisateur)
    {
        try {
            Utilisateur Utilisateur = repository.findByLoginAndPwd(utilisateur.getLogin(), utilisateur.getPwd());
            return Utilisateur ;
        }catch (Exception e)
        {
            System.out.println("Erreur login ou mot de passe!!!:"+e.getLocalizedMessage());
        }
        return null;
    }


    @PostMapping("/addUsers")
    public Utilisateur createUtilisateur(@RequestBody Utilisateur Utilisateur)

    {
        return repository.save(Utilisateur);
    }


    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUtilisateur(@PathVariable(value = "id") Long UtilisateurId)
            throws ResourceNotFoundException {
        Utilisateur Utilisateur = repository.findById(UtilisateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found  id :: " + UtilisateurId));

        repository.delete(Utilisateur);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    @DeleteMapping("/users/delete")
    public ResponseEntity<String> deleteAllUtilisateur() {
        System.out.println("Delete All Utilisateur...");

        repository.deleteAll();

        return new ResponseEntity<>("All Utilisateurs have been deleted!", HttpStatus.OK);
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable("id") long id, @RequestBody Utilisateur Utilisateur) {
        System.out.println("Update Utilisateur with ID = " + id + "...");

        Optional<Utilisateur> UtilisateurInfo = repository.findById(id);

        if (UtilisateurInfo.isPresent()) {
            Utilisateur utilisateur = UtilisateurInfo.get();
           // utilisateur.setRole(Utilisateur.isRole());
            utilisateur.setNom(Utilisateur.getNom());
            utilisateur.setLogin(Utilisateur.getLogin());
            utilisateur.setPwd(Utilisateur.getPwd());
            return new ResponseEntity<>(repository.save(Utilisateur), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
