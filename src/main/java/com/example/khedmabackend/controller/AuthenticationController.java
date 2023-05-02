package com.example.khedmabackend.controller;


import com.example.khedmabackend.authentification.AuthentificationRequest;
import com.example.khedmabackend.authentification.RegisterRequest;
import com.example.khedmabackend.authentification.ResponseToken;
import com.example.khedmabackend.authentification.service.AuthentificationService;
import com.example.khedmabackend.services.EmailService;
import com.example.khedmabackend.services.ServiceConfirmation;
import com.example.khedmabackend.services.ServiceEnregistrementFichier;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.InvalidKeyException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthentificationService authentificationService;
    private final ServiceEnregistrementFichier serviceEnregistrementFichier;
    private final ServiceConfirmation serviceConfirmation;

    private final EmailService emailService;



    @PostMapping("/add-user")
    //rest api ajout d'un utilisateur
    public ResponseEntity<ResponseToken> addUser(@RequestBody RegisterRequest register) throws Exception {
        ResponseToken token=authentificationService.save(register);
        emailService.sendMail(register.getAdresseMail(), register.getPrenom(),token.getToken());
        return ResponseEntity.status(201).body(token);
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
        ResponseToken token= authentificationService.Authenticate(authentificationRequest);
        return ResponseEntity.ok().body(token);
    }
}