package com.example.khedmabackend.controller;


import com.example.khedmabackend.model.*;
import com.example.khedmabackend.repo.AnnonceRepo;
import com.example.khedmabackend.repo.UtilisateurRepo;
import com.example.khedmabackend.services.EmployeurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/employeur")
@RestController
@RequiredArgsConstructor
public class EmployeurController {


    private final EmployeurService employeurService;

    @PostMapping("/add-annonce")
    public String addAnnonce(@RequestBody Annonce annonce){
        System.out.println("addAnnonce()");
        employeurService.addAnnonce(annonce);

        return "added succesfully";
    }

    @GetMapping("/annonces")
    public ResponseEntity<List<Annonce>> getAnnonces(){

        return ResponseEntity.ok().body(employeurService.getAnnonces());
    }

    @GetMapping("/trouver/{username}")
    public ResponseEntity<Utilisateur> trouver(@PathVariable(value = "username") String username ){
        return ResponseEntity.ok().body(employeurService.trouverUtilisateur(username));
    }
    @GetMapping("/all-Utilisateurs")
    public ResponseEntity<List<Utilisateur>> trouver(){

        return ResponseEntity.ok().body(employeurService.allUtilisateur());
    }

}
