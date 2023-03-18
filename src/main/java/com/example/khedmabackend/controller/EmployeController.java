package com.example.khedmabackend.controller;


import com.example.khedmabackend.model.Annonce;
import com.example.khedmabackend.repo.AnnonceRepo;
import com.example.khedmabackend.services.EmployeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employe")
@RequiredArgsConstructor
public class EmployeController {

    private final EmployeService employeService;
//    private final AnnonceRepo annonceRepo;


    @GetMapping("/annonces")
    public ResponseEntity<List<Annonce>> getAnnonces(){

        //
//        annonceRepo.

        return ResponseEntity.ok().body(employeService.getAnnonces());
    }
}
