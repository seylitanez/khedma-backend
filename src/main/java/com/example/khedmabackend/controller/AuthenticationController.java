package com.example.khedmabackend.controller;
import com.example.khedmabackend.authentification.AuthentificationRequest;
import com.example.khedmabackend.authentification.AuthentificationRequestGoogle;
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
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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


    @PostMapping("/test")
    public String getUserInfo(@RequestBody OAuth2AuthorizedClient authorizedClient) {
        System.out.println(authorizedClient);

        System.out.println("-----------------------------------------------------------------------------------");
        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        // Récupérer d'autres informations de l'utilisateur selon vos besoins
        System.out.println(accessToken);

        return accessToken;
    }

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