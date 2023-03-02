package com.example.khedmabackend.controller;


import com.example.khedmabackend.authentification.AuthentificationRegister;
import com.example.khedmabackend.model.Employe;
import com.example.khedmabackend.model.Employeur;
import com.example.khedmabackend.model.Moderateur;
import com.example.khedmabackend.model.Utilisateur;
import com.example.khedmabackend.repo.UtilisateurRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class userController {

    private final UtilisateurRepo utilisateurRepo;

    @PostMapping("/add-user")
    public ResponseEntity<Utilisateur> addUser(@RequestBody AuthentificationRegister register) throws Exception {

        System.out.println(register.toString());
        Utilisateur utilisateur = null;
        switch (register.getRole()){

            case EMPLOYE -> {
                utilisateurRepo.insert(
                        utilisateur=new Employe(
                                register.getNomUtilisateur(),
                                register.getMotDePasse(),
                                register.getAdresseMail(),
                                register.getNom(),
                                register.getPrenom(),
                                register.getGenre(),
                                register.getTel(),
                                register.getAdresse(),
                                register.getRole()
                                ));
            }
            case EMPLOYEUR -> {
                utilisateurRepo.insert(
                        utilisateur=new Employeur(
                                register.getNomUtilisateur(),
                                register.getMotDePasse(),
                                register.getAdresseMail(),
                                register.getNom(),
                                register.getPrenom(),
                                register.getGenre(),
                                register.getTel(),
                                register.getAdresse(),
                                register.getRole()
                        ));
            }
            case MODERATEUR -> {
                utilisateurRepo.insert(
                        utilisateur = new Moderateur(
                                register.getNomUtilisateur(),
                                register.getMotDePasse(),
                                register.getAdresseMail(),
                                register.getNom(),
                                register.getPrenom(),
                                register.getGenre(),
                                register.getTel(),
                                register.getAdresse(),
                                register.getRole()
                                ));
            }
        }
        if (utilisateur==null)throw new Exception("user null");

        return ResponseEntity.status(201).body(utilisateur);
    }


}
