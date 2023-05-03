package com.example.khedmabackend.services;
import com.example.khedmabackend.model.Annonce;
import com.example.khedmabackend.model.Utilisateur;
import com.example.khedmabackend.repo.AnnonceRepo;
import com.example.khedmabackend.repo.UtilisateurGoogleRepo;
import com.example.khedmabackend.repo.UtilisateurRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EmployeService {
    private final UtilisateurRepo utilisateurRepo;
    private final UtilisateurGoogleRepo utilisateurGoogleRepo;
    private final AnnonceRepo annonceRepo;
    public List<Annonce> getAnnonces(){
        return annonceRepo.findAll();
    }
    public List<Annonce> searchAnnonces(String motCle){
        return annonceRepo.searchAnnonces(motCle);
    }
    public Utilisateur updateEmploye(String email, String nom, String prenom, String tel){
        Utilisateur utilisateur =(Utilisateur) utilisateurRepo.findByadresseMail(email)
                .or(()->utilisateurGoogleRepo.findByadresseMail(email))
                .orElseThrow();
        if(nom!=null && nom.length()>0 && !nom.equals(utilisateur.getNom())){
            utilisateur.setNom(nom);
        }
        if(prenom!=null && prenom.length()>0 && !prenom.equals(utilisateur.getPrenom())){
            utilisateur.setPrenom(prenom);
        }
        if(tel!=null && tel.length()>0 && !tel.equals(utilisateur.getTel())){
            utilisateur.setTel(tel);
        }
        return utilisateurRepo.save(utilisateur);
    }
}