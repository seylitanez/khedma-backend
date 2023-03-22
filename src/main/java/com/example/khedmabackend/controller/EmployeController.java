package com.example.khedmabackend.controller;


import com.example.khedmabackend.model.Annonce;
import com.example.khedmabackend.services.EmployeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employe")
@RequiredArgsConstructor
public class EmployeController {
    private final EmployeService employeService;
    @GetMapping("/annonces")
    //api de list d'annonces
    public ResponseEntity<List<Annonce>> getAnnonces(){
        return ResponseEntity.ok().body(employeService.getAnnonces());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Annonce>> searchAnnonces(@RequestParam("motcle") String motCle){

        return ResponseEntity.ok().body(employeService.searchAnnonces(motCle));
    }
}
