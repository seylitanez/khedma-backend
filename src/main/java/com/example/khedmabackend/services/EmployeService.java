package com.example.khedmabackend.services;
import com.example.khedmabackend.model.*;
import com.example.khedmabackend.postulation.Postulation;
import com.example.khedmabackend.repo.AnnonceRepo;
import com.example.khedmabackend.repo.UtilisateurGoogleRepo;
import com.example.khedmabackend.repo.UtilisateurRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
        if (!utilisateurRepo.findByadresseMail(email).isPresent())throw new RuntimeException("user doesen't exist");
        UtilisateurGoogle utilisateur =utilisateurGoogleRepo.findByadresseMail(email)
                .or(()->  utilisateurRepo.findByadresseMail(email))
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
        return utilisateurRepo.save((Utilisateur) utilisateur);
    }


    public void addFavoris(String idAnnonce){

        var myEmail= SecurityContextHolder.getContext().getAuthentication().getName();
        var isUtilisateur= utilisateurRepo.findByadresseMail(myEmail).isPresent();
        var annonce= annonceRepo.findById(idAnnonce).orElseThrow();
        if(isUtilisateur) {
        var employe= (Employe) utilisateurRepo.findByadresseMail(myEmail)
                .orElseThrow();
        employe.getFavoris().add(annonce);
        utilisateurRepo.save(employe);
        employe.getFavoris().add(annonce);
        utilisateurRepo.save(employe);
        }
        var isGoogleUtilisateur= utilisateurGoogleRepo.findByadresseMail(myEmail).isPresent();
        System.out.println("l'email avant le if"+myEmail);
        if (isGoogleUtilisateur){

         var employe=(Employe) utilisateurGoogleRepo.findByEmail(myEmail);
            System.out.println(employe);
        employe.getFavoris().add(annonce);
        utilisateurGoogleRepo.save(employe);
        }



    }
    public List<Annonce> getFavoris(String email) {
        Employe utilisateur =(Employe) utilisateurRepo.findByadresseMail(email).orElseThrow();
        return utilisateur.getFavoris();
    }

    public void postuler(Postulation postulation, String idAnnonce) {

        var myAdresseMail=SecurityContextHolder.getContext().getAuthentication().getName();

        var me=utilisateurGoogleRepo
                .findByadresseMail(myAdresseMail)
                .or(()->utilisateurRepo.findByadresseMail(myAdresseMail))
                .orElseThrow();//je cherche dans les repogoogle sinon dans le repo non google


        //verification dans le repo des utilisateur non google
        var isNotGoogleUser= utilisateurRepo.findUtilisateurByAnnonce(idAnnonce).isPresent();
        if (isNotGoogleUser)
        {
            //creation de ma postulation
           postulation= postulation.builder()
                    .nom(me.getNom())
                    .prenom(me.getPrenom())
                    .genre(me.getGenre())
                    .adresseMail(me.getAdresseMail()).build();

            var employeur = (Employeur) utilisateurRepo.findUtilisateurByAnnonce(idAnnonce).orElseThrow();
            System.out.println(employeur);
            employeur.getPostulants().add(postulation);
            utilisateurRepo.save(employeur);
        }
        //verification dans le repo des utilisateur google
        var isGoogleUser=utilisateurGoogleRepo.findUtilisateurByAnnonce(idAnnonce).isPresent();
        if (isGoogleUser)
        {
            //creation de ma postulation
            postulation= postulation.builder()
                    .nom(me.getNom())
                    .prenom(me.getPrenom())
                    .genre(me.getGenre())
                    .adresseMail(me.getAdresseMail()).build();
            var employeur = (Employeur) utilisateurGoogleRepo.findUtilisateurByAnnonce(idAnnonce).orElseThrow();
            employeur.getPostulants().add(postulation);
            utilisateurGoogleRepo.save(employeur);
        }

        else throw new IllegalStateException("not found");//si ca ne veut pas s'executer supprimmer cette ligne
    }
}