package com.example.khedmabackend.controller;


import com.example.khedmabackend.model.Annonce;
import com.example.khedmabackend.model.Utilisateur;
import com.example.khedmabackend.repo.UtilisateurGoogleRepo;
import com.example.khedmabackend.services.EmployeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employe")
@RequiredArgsConstructor
public class EmployeController {
    private final EmployeService employeService;
    @GetMapping("/annonces")
    //api de list d'annonces
    public ResponseEntity<List<Annonce>> getAnnonces(){
        return ResponseEntity.ok().body(employeService.getAnnonces());
    }

    //ajout aux favoris
    @GetMapping("/add-favoris")
    public HttpStatus addFavoris(@RequestParam(value = "idAnnonce")String idAnnonce){
        employeService.addFavoris(idAnnonce);
        System.out.println("add favoris -----");
        return HttpStatus.CREATED;
    }
    @GetMapping("/search")
    public ResponseEntity<List<Annonce>> searchAnnonces(@RequestParam("motcle") String motCle){
        return ResponseEntity.ok().body(employeService.searchAnnonces(motCle));
    }
    @PutMapping("/update/{email}")
    public ResponseEntity<Utilisateur> updateUser(@PathVariable("email")String email,@RequestParam(required = false) String nom,@RequestParam(required = false) String prenom,@RequestParam(required = false) String tel){
        return ResponseEntity.ok().body(employeService.updateEmploye(email,nom,prenom,tel));
    }
    @GetMapping("/favoris")
    public ResponseEntity<List<Annonce>> getFavoris(@RequestParam("email") String email){
        return ResponseEntity.ok().body(employeService.getFavoris(email));
    }
}
