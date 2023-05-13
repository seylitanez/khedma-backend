package com.example.khedmabackend.controller;
import com.example.khedmabackend.authentification.AuthentificationRequest;
import com.example.khedmabackend.authentification.AuthentificationRequestGoogle;
import com.example.khedmabackend.authentification.RegisterRequest;
import com.example.khedmabackend.authentification.ResponseToken;
import com.example.khedmabackend.authentification.service.AuthentificationService;
import com.example.khedmabackend.config.JwtService;
import com.example.khedmabackend.model.Utilisateur;
import com.example.khedmabackend.model.UtilisateurGoogle;
import com.example.khedmabackend.repo.UtilisateurGoogleRepo;
import com.example.khedmabackend.repo.UtilisateurRepo;
import com.example.khedmabackend.services.EmailService;
import com.example.khedmabackend.services.ServiceConfirmation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthentificationService authentificationService;
    private final ServiceConfirmation serviceConfirmation;
    private final EmailService emailService;
    private final UtilisateurGoogleRepo utilisateurGoogleRepo;
    private final UtilisateurRepo utilisateurRepo;

    private final JwtService jwtService;
    @PostMapping("/add-user")
    //rest api ajout d'un utilisateur
    public ResponseEntity<?> addUser(@RequestBody RegisterRequest register) throws Exception {
        ResponseToken token=authentificationService.save(register);
        emailService.sendMail(register.getAdresseMail(), register.getPrenom(),token.getToken());
        try {
            return ResponseEntity.status(201).body(token);
        }catch (Exception e){
            return ResponseEntity.status(401).body(e.getMessage());// Renvoyer l'erreur au frontend
        }
    }
    @PostMapping("/add-GoogleUser")
    //rest api ajout d'un utilisateur google
    public ResponseEntity<?> addGoogleUser(@RequestBody RegisterRequest register) {
        try {
            ResponseToken token = authentificationService.saveGoogleUser(register);
            return ResponseEntity.status(201).body(token);
        } catch (Exception e) {
            // Renvoyer l'erreur au frontend

            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
    @GetMapping("/googleRepo")
    //rest api ajout d'un utilisateur google
    public ResponseEntity<?> getGoogleUsers() throws Exception {
        var utilisateur= utilisateurGoogleRepo.findAll();
        try {
            return ResponseEntity.status(201).body(utilisateur);
        }catch (Exception e){
            return ResponseEntity.status(401).body(e.getMessage());// Renvoyer l'erreur au frontend
        }
    }
    @GetMapping("/utilisateurRepo")
    //rest api ajout d'un utilisateur google
    public ResponseEntity<?> getUsers() throws Exception {

        var utilisateur= utilisateurRepo.findAll();
        try{
            return ResponseEntity.status(201).body(utilisateur);
        }catch (Exception e){
            return ResponseEntity.status(401).body(e.getMessage());// Renvoyer l'erreur au frontend
        }
    }

    @GetMapping("/confirm")
    public void confirm(@RequestParam(value = "token") String token) throws InvalidKeyException {
        serviceConfirmation.confirm(token);
    }
    @PostMapping("/login")
    //rest api de conction d'un utilisateur
    public ResponseEntity<?> login(@RequestBody AuthentificationRequest authentificationRequest){
        try {
            ResponseToken token= authentificationService.authenticate(authentificationRequest);
            return ResponseEntity.ok().body(token);
        }catch (Exception e) {
            return ResponseEntity.status(401).body("l'utilisateur n'existe pas ou bien le mot de passe est incorrecte");
        }
    }
    @PostMapping("/Google-login")
    //rest api de conction d'un utilisateur
    public ResponseEntity<?> googlelogin(@RequestBody AuthentificationRequestGoogle authentificationRequestGoogle)  {
        try {
            ResponseToken token= authentificationService.authenticateGoogle(authentificationRequestGoogle);
            return ResponseEntity.ok().body(token);
        }catch (Exception e){
            return ResponseEntity.status(401).body("l'utilisateur n'existe pas");
        }
    }
    //mot de passe oublié
//    @PostMapping("/forgot-password")
//    public ResponseEntity<String> forgotPassword(@RequestBody AuthentificationRequest authentificationRequest) throws Exception {
//        authentificationService.forgotPassword(authentificationRequest);
//        return ResponseEntity.ok().body("email envoyé");
//    }
}