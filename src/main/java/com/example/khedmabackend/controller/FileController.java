package com.example.khedmabackend.controller;

import com.example.khedmabackend.services.ServiceEnregistrementFichier;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/document")
@RequiredArgsConstructor
public class FileController {

    private final ServiceEnregistrementFichier serviceEnregistrementFichier;
    @PostMapping("/fich")
    public ResponseEntity<String> creationRepo(@RequestParam("file") MultipartFile file) throws IOException {
        serviceEnregistrementFichier.addCv(file);
        return ResponseEntity.status(200).build();
    }
}
