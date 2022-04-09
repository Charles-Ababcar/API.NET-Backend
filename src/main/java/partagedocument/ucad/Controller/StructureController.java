package partagedocument.ucad.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import partagedocument.ucad.Models.Structure;
import partagedocument.ucad.Models.Utilisateur;
import partagedocument.ucad.Repository.StructureRepository;
import partagedocument.ucad.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StructureController {
    @Autowired
    StructureRepository structureRepository;
    /*@RequestMapping(value="/add", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}
          )*/
    @PostMapping("/addStructure")
    public Structure createStructure(@RequestBody Structure structure)
    {
        return structureRepository.save(structure);
    }
    @GetMapping("/allStructure")
    public List<Structure> allStructure(){
        System.out.println("Get Structure.....");
          List<Structure> structures=new ArrayList<>();
          structureRepository.findAll().forEach(structures::add);
          return structures;
    }
    @GetMapping("/allStructures/{nom}")
    public  List<Structure> allStructureRequete(@PathVariable String nom){
        System.out.println("Get Structure.....");
        List<Structure> structures=new ArrayList<>();
        structureRepository.findByNomContains(nom);
       return structures;
    }
    @GetMapping("/structure/{id}")
    public ResponseEntity<Structure> getStructureById(@PathVariable(value = "id") Long structureId)
            throws ResourceNotFoundException {
        Structure structure = structureRepository.findById(structureId)
                .orElseThrow(() -> new ResourceNotFoundException("Structure not found for this id :: " + structureId));
        return ResponseEntity.ok().body(structure);
    }
    //Modifié une structure
    @PutMapping("modifierStructure/{id}")
    public String ModifierStructure(@RequestBody Structure structure) {
        if (structure == null) {
            return "La structure n'existe";
        } else {
            structure.setNom(structure.getNom());
            structure.setAdresse(structure.getCapacite());
            structure.setContact(structure.getContact());
           structure.setNbresplaces(structure.getNbresplaces());
            structure.setNombredoses(structure.getNombredoses());
            structureRepository.save(structure);
            return "structure modifé avec succès!";
        }
    }

}