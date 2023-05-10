package com.example.khedmabackend.controller;


import com.example.khedmabackend.model.Annonce;
import com.example.khedmabackend.services.ModerateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/moderateur")
@RequiredArgsConstructor
public class ModerateurController {
    private final ModerateurService moderateurService;
    @GetMapping("/annonces")
    //list d'annoce
    public ResponseEntity<List<Annonce>> getAnnonces(){
        return ResponseEntity.ok().body(moderateurService.getAnnonces());
    }
    @DeleteMapping("/delete-annonce/{idAnnonce}")
    public String deleteAnnonce(@PathVariable("idAnnonce") String idAnnonce){
        moderateurService.deleteAnnonce(idAnnonce);
        return "deleted successfully";
    }

    @GetMapping("/UtilisateursCount")
    public ResponseEntity<Long> getUtilisateursCount(){

        return ResponseEntity.ok().body(moderateurService.getUtilisateursCount());
    }
    @GetMapping("/GoogleUtilisateursCount")
    public ResponseEntity<Long> getGoogleUtilisateursCount(){

        return ResponseEntity.ok().body(moderateurService.getGoogleUtilisateursCount());
    }
    @GetMapping("/nonGoogleUtilisateursCount")
    public ResponseEntity<Long> getNonGoogleUtilisateursCount(){

        return ResponseEntity.ok().body(moderateurService.getNonGoogleUtilisateursCount());
    }
    @GetMapping("/employeCount")
    public ResponseEntity<Long> getEmployeCount(){

        return ResponseEntity.ok().body(moderateurService.getEmployeCount());
    }
    @GetMapping("/employeurCount")
    public ResponseEntity<Long> getEmployeurCount(){

        return ResponseEntity.ok().body(moderateurService.getEmployeurCount());
    }
    @GetMapping("/femmeCount")
    public ResponseEntity<Long> getFemmeCount(){

        return ResponseEntity.ok().body(moderateurService.getFemmeCount());
    }
    @GetMapping("/hommeCount")
    public ResponseEntity<Long> homme(){

        return ResponseEntity.ok().body(moderateurService.getHommeCount());
    }

}