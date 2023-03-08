package com.example.khedmabackend.services;


import com.example.khedmabackend.model.Annonce;
import com.example.khedmabackend.model.Employeur;
import com.example.khedmabackend.model.Utilisateur;
import com.example.khedmabackend.repo.AnnonceRepo;
import com.example.khedmabackend.repo.UtilisateurRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeurService {


    private final UtilisateurRepo utilisateurRepo;
    private final AnnonceRepo annonceRepo;

    public void addAnnonce(Annonce annonce){
        var username= SecurityContextHolder.getContext().getAuthentication().getName();

        Employeur employeur= (Employeur) utilisateurRepo.findBynomUtilisateur(username).orElseThrow();
        annonceRepo.insert(annonce);
        employeur.getAnnonces().add(annonce);
        utilisateurRepo.save(employeur);

    }
    public List<Annonce> getAnnonces(){

        var employeurUsername=SecurityContextHolder.getContext().getAuthentication().getName();
        Employeur employeur= (Employeur) utilisateurRepo.findBynomUtilisateur(employeurUsername).orElseThrow();
        return (employeur.getAnnonces());
    }

    public Utilisateur trouverUtilisateur(String username){
        var utilisateur= utilisateurRepo.findBynomUtilisateur(username).orElseThrow();

        return utilisateur;
    }

    public List<Utilisateur> allUtilisateur(){

        return utilisateurRepo.findAll();
    }



}
