package com.example.khedmabackend.controller;


import com.example.khedmabackend.authentification.AuthentificationRequest;
import com.example.khedmabackend.authentification.RegisterRequest;
import com.example.khedmabackend.authentification.ResponseToken;
import com.example.khedmabackend.authentification.service.AuthentificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthentificationService authentificationService;

    @PostMapping("/add-user")
    public ResponseEntity<ResponseToken> addUser(@RequestBody RegisterRequest register) throws Exception {

        ResponseToken token=authentificationService.save(register);



        return ResponseEntity.status(201).body(token);
    }


    @PostMapping("/login")
    public ResponseEntity<ResponseToken> login(@RequestBody AuthentificationRequest authentificationRequest){


        ResponseToken token= authentificationService.Authenticate(authentificationRequest);

        return ResponseEntity.ok().body(token);

    }

}
