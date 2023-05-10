package com.example.khedmabackend.services;

import com.example.khedmabackend.model.Utilisateur;
import com.example.khedmabackend.model.UtilisateurGoogle;
import com.example.khedmabackend.repo.UtilisateurGoogleRepo;
import com.example.khedmabackend.repo.UtilisateurRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MeService {
    private final UtilisateurRepo utilisateurRepo;
    private final UtilisateurGoogleRepo utilisateurGoogleRepo;
    public UtilisateurGoogle me(){
        String myName=SecurityContextHolder.getContext().getAuthentication().getName();
        UtilisateurGoogle utilisateur= utilisateurGoogleRepo.findByadresseMail(myName)
                .or(()->utilisateurRepo.findByadresseMail(myName))
                .orElseThrow();

        return utilisateur;
    }
}