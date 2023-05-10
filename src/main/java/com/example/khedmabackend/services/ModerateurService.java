package com.example.khedmabackend.services;
import com.example.khedmabackend.model.Annonce;
import com.example.khedmabackend.model.Employeur;
import com.example.khedmabackend.model.Genre;
import com.example.khedmabackend.repo.AnnonceRepo;
import com.example.khedmabackend.repo.UtilisateurGoogleRepo;
import com.example.khedmabackend.repo.UtilisateurRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.khedmabackend.model.Genre.FEMME;
import static com.example.khedmabackend.model.Genre.HOMME;
import static com.example.khedmabackend.model.Role.EMPLOYE;
import static com.example.khedmabackend.model.Role.EMPLOYEUR;

@Service
@RequiredArgsConstructor
public class ModerateurService {
    private final UtilisateurRepo utilisateurRepo;
    private final UtilisateurGoogleRepo utilisateurGoogleRepo;
    private final AnnonceRepo annonceRepo;
    public List<Annonce> getAnnonces(){
        return annonceRepo.findAll();
    }

    public void deleteAnnonce(String idAnnonce) {

        var annonce=annonceRepo.findById(idAnnonce).orElseThrow();

        //verification dans le repo des utilisateur non google
        var isNotGoogleUser= utilisateurRepo.findUtilisateurByAnnonce(idAnnonce).isPresent();
        if (isNotGoogleUser)
        {
            var employeur = (Employeur) utilisateurRepo.findUtilisateurByAnnonce(idAnnonce).orElseThrow();
            employeur.getAnnonces().remove(annonce);
            annonceRepo.delete(annonce);
            utilisateurRepo.save(employeur);
        }
        //verification dans le repo des utilisateur google
        var isGoogleUser=utilisateurGoogleRepo.findUtilisateurByAnnonce(idAnnonce).isPresent();
        if (isGoogleUser)
        {
            var employeur = (Employeur) utilisateurGoogleRepo.findUtilisateurByAnnonce(idAnnonce).orElseThrow();
            employeur.getAnnonces().remove(annonce);
            annonceRepo.delete(annonce);
            utilisateurGoogleRepo.save(employeur);
        }
    }



    public Long getUtilisateursCount() {
       return utilisateurRepo.count()
               +
               utilisateurGoogleRepo.count();
    }

    public Long getGoogleUtilisateursCount() {

       return utilisateurGoogleRepo.count();

    }
    public Long getNonGoogleUtilisateursCount() {

       return utilisateurRepo.count();
    }

    public Long getEmployeCount(){
        return utilisateurGoogleRepo.findUtilisateurGoogleByrole(EMPLOYE).stream().count()
                +
                utilisateurRepo.findUtilisateurByrole(EMPLOYE).stream().count();
    }
    public Long getEmployeurCount(){
        return utilisateurGoogleRepo.findUtilisateurGoogleByrole(EMPLOYEUR).stream().count()
                +
                utilisateurRepo.findUtilisateurByrole(EMPLOYEUR).stream().count();
    }

    public Long getFemmeCount(){
        return utilisateurRepo.findUtilisateurBygenre(FEMME).stream().count()
                +
                utilisateurGoogleRepo.findUtilisateurGoogleBygenre(FEMME).stream().count();
    }
    public Long getHommeCount(){
        return utilisateurRepo.findUtilisateurBygenre(HOMME).stream().count()
                +
                utilisateurGoogleRepo.findUtilisateurGoogleBygenre(HOMME).stream().count();
    }



}