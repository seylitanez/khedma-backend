package com.example.khedmabackend.controller;


import com.example.khedmabackend.authentification.AuthentificationRequest;
import com.example.khedmabackend.authentification.RegisterRequest;
import com.example.khedmabackend.authentification.ResponseToken;
import com.example.khedmabackend.authentification.service.AuthentificationService;
import com.example.khedmabackend.model.Utilisateur;
import com.example.khedmabackend.model.UtilisateurGoogle;
import com.example.khedmabackend.repo.UtilisateurGoogleRepo;
import com.example.khedmabackend.repo.UtilisateurRepo;
import com.example.khedmabackend.services.EmailService;
import com.example.khedmabackend.services.ServiceConfirmation;
import com.example.khedmabackend.services.ServiceEnregistrementFichier;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthentificationService authentificationService;
    private final ServiceEnregistrementFichier serviceEnregistrementFichier;
    private final ServiceConfirmation serviceConfirmation;

    private final EmailService emailService;
    private final UtilisateurGoogleRepo utilisateurGoogleRepo;
    private final UtilisateurRepo utilisateurRepo;




    @PostMapping("/add-user")
    //rest api ajout d'un utilisateur
    public ResponseEntity<ResponseToken> addUser(@RequestBody RegisterRequest register) throws Exception {
        ResponseToken token=authentificationService.save(register);
//        emailService.sendMail(register.getAdresseMail(), register.getPrenom(),token.getToken());
        return ResponseEntity.status(201).body(token);
    }

    @PostMapping("/add-GoogleUser")
    //rest api ajout d'un utilisateur google
    public ResponseEntity<ResponseToken> addGoogleUser(@RequestBody RegisterRequest register) throws Exception {
        ResponseToken token=authentificationService.saveGoogleUser(register);
        return ResponseEntity.status(201).body(token);
    }
    @GetMapping("/googleRepo")
    //rest api ajout d'un utilisateur google
    public ResponseEntity<List<UtilisateurGoogle>> getGoogleUsers() throws Exception {

        System.out.println("get google users");
        var utilisateur= utilisateurGoogleRepo.findAll();
        return ResponseEntity.status(201).body(utilisateur);
    }
    @GetMapping("/utilisateurRepo")
    //rest api ajout d'un utilisateur google
    public ResponseEntity<List<Utilisateur>> getUsers() throws Exception {
        System.out.println("get users");
        var utilisateur= utilisateurRepo.findAll();
        return ResponseEntity.status(201).body(utilisateur);
    }


    @PostMapping("/fich")
    public ResponseEntity<String> creationRepo(@RequestParam("file") MultipartFile file) throws IOException {

        serviceEnregistrementFichier.addCv(file);

        return ResponseEntity.status(200).build();
    }

//    @GetMapping("/test")
//    public ModelAndView pageHtml(){
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.setViewName("index.html");
//        System.out.println(modelAndView.getModel());
//        return modelAndView;
//    }
    @GetMapping("/confirm")
    public void confirm(@RequestParam(value = "token") String token) throws InvalidKeyException {
        serviceConfirmation.confirm(token);
    }
    @PostMapping("/login")
    //rest api de conction d'un utilisateur
    public ResponseEntity<ResponseToken> login(@RequestBody AuthentificationRequest authentificationRequest){
        ResponseToken token= authentificationService.authenticate(authentificationRequest);
        return ResponseEntity.ok().body(token);
    }
    @PostMapping("/Google-login")
    //rest api de conction d'un utilisateur
    public ResponseEntity<ResponseToken> googlelogin(@RequestBody AuthentificationRequest authentificationRequest){
        ResponseToken token= authentificationService.authenticateGoogle(authentificationRequest);
        return ResponseEntity.ok().body(token);
    }
}