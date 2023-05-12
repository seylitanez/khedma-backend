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
        var utilisateur= utilisateurGoogleRepo.findAll();
        return ResponseEntity.status(201).body(utilisateur);
    }
    @GetMapping("/utilisateurRepo")
    //rest api ajout d'un utilisateur google
    public ResponseEntity<List<Utilisateur>> getUsers() throws Exception {
        var utilisateur= utilisateurRepo.findAll();
        return ResponseEntity.status(201).body(utilisateur);
    }
//    @GetMapping("/test")
//    public ModelAndView pageHtml(){
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.setViewName("index.html");
//        System.out.println(modelAndView.getModel());
//        return modelAndView;
//    }


//    @PostMapping("/test")
//    public String getUserInfo(@RequestBody Test test) throws Exception {
//        System.out.println(test);
//        System.out.println("-*/-*-*-/-*-*/*/test");
//        System.out.println();
//        var email=jwtService.extractTest(test.getToken());
//
//        System.out.println("l'utilisateur "+ utilisateurGoogleRepo.findByadresseMail(email).orElseThrow());
//
//        if (!jwtService.isTokenValid(test.getToken()))throw new Exception("non valide");
//        System.out.println(jwtService.extractUsername(test.getToken()));
//
//
//
//
//        return "test";
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
    public ResponseEntity<ResponseToken> googlelogin(@RequestBody AuthentificationRequestGoogle authentificationRequestGoogle) throws Exception {
        System.out.println(authentificationRequestGoogle);
        ResponseToken token= authentificationService.authenticateGoogle(authentificationRequestGoogle);
        return ResponseEntity.ok().body(token);
    }
}