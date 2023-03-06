package com.example.khedmabackend.controller;


import com.example.khedmabackend.model.*;
import com.example.khedmabackend.repo.AnnonceRepo;
import com.example.khedmabackend.repo.UtilisateurRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/employeur")
@RestController
@RequiredArgsConstructor
public class EmployeurController {

    private final UtilisateurRepo utilisateurRepo;
    private final AnnonceRepo annonceRepo;

    @GetMapping("/add-annconce")
    public String addAnnonce(@RequestBody Annonce annonce){
        var username=SecurityContextHolder.getContext().getAuthentication().getName();

        Employeur employeur= (Employeur) utilisateurRepo.findBynomUtilisateur(username).orElseThrow();
//        annonceRepo.insert():
//        Annonce annonce=new Annonce(null,"cuisinier",Categorie.CHEF_CUISINIER,"s.cuisine",1500f,new Jours[]{Jours.DIMANCHE},new Date(System.currentTimeMillis()));
        annonceRepo.insert(annonce);
        employeur.getAnnonces().add(annonce);
        utilisateurRepo.save(employeur);

        return "hi";
    }

    @GetMapping("/trouver/{nom}")
    public ResponseEntity<List<Utilisateur>> trouver(@PathVariable(value = "nom") String nom ){
//        System.out.println(utilisateurRepo.trouver("lyes"));
//        System.out.println(utilisateurRepo.trouver("sabrine"));
        System.out.println(utilisateurRepo.findBynomUtilisateur("lyes"));

        return ResponseEntity.ok().body(utilisateurRepo.findByprenom(nom));
    }
//    private String nom;
//    private Categorie categorie;
//    private String sousCategorie;
//    private Float salaireDeBase;
//    private Jours[] journees=new Jours[7];
//    private Date date
}
