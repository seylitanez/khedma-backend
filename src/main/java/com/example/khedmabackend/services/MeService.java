package com.example.khedmabackend.services;

import com.example.khedmabackend.model.Utilisateur;
import com.example.khedmabackend.repo.UtilisateurRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MeService {
    private final UtilisateurRepo utilisateurRepo;
    public Utilisateur me(){
        String myName=SecurityContextHolder.getContext().getAuthentication().getName();
        Utilisateur utilisateur= utilisateurRepo.findBynomUtilisateur(myName).orElseThrow();
        return utilisateur;
    }
}