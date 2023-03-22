package com.example.khedmabackend.controller;


import com.example.khedmabackend.model.Utilisateur;
import com.example.khedmabackend.services.MeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/me")
@RequiredArgsConstructor
public class MoiController {
    private final MeService meService;
    // renvoi les information de mon profile
    @GetMapping("")
    public Utilisateur me(){
        return meService.me();
    }
}
