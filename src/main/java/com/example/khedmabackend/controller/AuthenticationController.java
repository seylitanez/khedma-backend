package com.example.khedmabackend.controller;


import com.example.khedmabackend.authentification.AuthentificationRequest;
import com.example.khedmabackend.authentification.RegisterRequest;
import com.example.khedmabackend.authentification.ResponseToken;
import com.example.khedmabackend.authentification.service.AuthentificationService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthentificationService authentificationService;
    @PostMapping("/add-user")
    //rest api ajout d'un utilisateur
    public ResponseEntity<ResponseToken> addUser(@RequestBody RegisterRequest register) throws Exception {
        ResponseToken token=authentificationService.save(register);
        return ResponseEntity.status(201).body(token);
    }

    @PostMapping("/fich")
    public ResponseEntity<String> fichier(@RequestParam("file") MultipartFile file) throws IOException {

        var fichierDecompose=file.getOriginalFilename().split("\\.");

        String nomFichier=fichierDecompose[0];
        String extensionFichier=fichierDecompose[1];

        if (extensionFichier.contains("png")||extensionFichier.contains("pdf")||extensionFichier.contains("jpg")){
        System.out.println("description "+file.getResource().getDescription());
            File dossier=new File(nomFichier);
            dossier.mkdir();
        IOUtils.copy(file.getInputStream(),new FileOutputStream(dossier.getName()+"/"+file.getOriginalFilename()));

        }else
            return ResponseEntity.status(403).body("format not supported");
        return ResponseEntity.status(200).build();
    }
    @PostMapping("/login")
    //rest api de conction d'un utilisateur
    public ResponseEntity<ResponseToken> login(@RequestBody AuthentificationRequest authentificationRequest){
        ResponseToken token= authentificationService.Authenticate(authentificationRequest);
        return ResponseEntity.ok().body(token);
    }
}