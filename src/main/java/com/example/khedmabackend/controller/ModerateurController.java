package com.example.khedmabackend.controller;


import com.example.khedmabackend.model.Annonce;
import com.example.khedmabackend.services.ModerateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/moderateur")
@RequiredArgsConstructor
public class ModerateurController {

    private final ModerateurService moderateurService;


    @GetMapping("/annonces")
    public ResponseEntity<List<Annonce>> getAnnonces(){

        return ResponseEntity.ok().body(moderateurService.getAnnonces());
    }



}
