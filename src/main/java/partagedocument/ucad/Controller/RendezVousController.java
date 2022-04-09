package partagedocument.ucad.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import partagedocument.ucad.Models.Rendezvous;

import partagedocument.ucad.Models.Structure;
import partagedocument.ucad.Repository.RendezVousRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RendezVousController{
@Autowired
    RendezVousRepository rendezVousRepository;

   @PostMapping("/addRv")
   public  String createrendezvous(@RequestBody Rendezvous rendezvous){
       rendezVousRepository.save(rendezvous);
       return "Votre Rendez Vous à été prise en compte: le "
               +rendezvous.getDateRV() +"dans le centre hospitalier"+rendezvous.getStructure();
              /* +rendezvous.getUtilisateur();*/
   }
    @GetMapping("/allRv")
    public List<Rendezvous> allRendezVous(){
        System.out.println("Get Structure.....");
        List<Rendezvous> rendezvous=new ArrayList<>();
        rendezVousRepository.findAll().forEach(rendezvous::add);
        return rendezvous;
    }
    @GetMapping("/getRendezVoustByid/{id}")
    public List<Rendezvous> getRendezByID(@PathVariable Long id) {
        List<Rendezvous> rendezvous1=new ArrayList<>();
        rendezVousRepository.getRendezvousBy(id).forEach(rendezvous1 ::add);
        return rendezVousRepository.getRendezvousBy(id);

    }
    @GetMapping("/getRv/{id}")
    public Rendezvous getRv(@PathVariable Long id){
       return rendezVousRepository.findById(id).get();
    }
}
