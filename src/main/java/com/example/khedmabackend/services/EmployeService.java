package com.example.khedmabackend.services;
import com.example.khedmabackend.model.*;
import com.example.khedmabackend.postulation.Postulation;
import com.example.khedmabackend.repo.AnnonceRepo;
import com.example.khedmabackend.repo.UtilisateurGoogleRepo;
import com.example.khedmabackend.repo.UtilisateurRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
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
        }
        var isGoogleUtilisateur= utilisateurGoogleRepo.findByadresseMail(myEmail).isPresent();
        System.out.println("l'email avant le if"+myEmail);
        if (isGoogleUtilisateur){

         var employe=(Employe) utilisateurGoogleRepo.findByEmail(myEmail);

        employe.getFavoris().add(annonce);
        utilisateurGoogleRepo.save(employe);
        }



    }
    public List<Annonce> getFavoris(String email) {
        Employe utilisateur =(Employe) utilisateurGoogleRepo.findByadresseMail(email)
                .or(()->utilisateurRepo.findByadresseMail(email))
                .orElseThrow();
        return utilisateur.getFavoris();
    }

    public void postuler(String idAnnonce) {

        var myAdresseMail=SecurityContextHolder.getContext().getAuthentication().getName();


        var me=utilisateurGoogleRepo
                .findByadresseMail(myAdresseMail)
                .or(()->utilisateurRepo.findByadresseMail(myAdresseMail))
                .orElseThrow();//je cherche dans les repogoogle sinon dans le repo non google

        File myRepo=new File("images/"+me.getId());
        var monCv= myRepo.getAbsoluteFile().listFiles()[0].getName();//je recupere le nom de mon fichier



            Annonce annonce= annonceRepo.findAnnonceByid(idAnnonce).orElseThrow();
        System.out.println("l'annonce est "+annonce);
        //verification dans le repo des utilisateur non google
        var nonGoogleUserExist= utilisateurRepo.findUtilisateurByAnnonce(idAnnonce).isPresent();
        System.out.println("l'utilisateur non google existe "+nonGoogleUserExist);
        if (nonGoogleUserExist)
        {
            //creation de ma postulation

           var postulation= Postulation.builder()
                    .nom(me.getNom())
                    .prenom(me.getPrenom())
                    .genre(me.getGenre())
                    .adresseMail(me.getAdresseMail())
                    .cv(monCv)
                   .build();

            var employeur = (Employeur) utilisateurRepo.findUtilisateurByAnnonce(idAnnonce).orElseThrow();
            annonce.getPostulants().add(postulation);
            for (Annonce n:employeur.getAnnonces()) {
                if (n.getId().equals(idAnnonce))
                    n.getPostulants().add(postulation);
            }
//            employeur.getAnnonces().get(employeur.getAnnonces().indexOf(annonce)).getPostulants().add(postulation);
            System.out.println(employeur);
            employeur.getPostulants().add(postulation);
            utilisateurRepo.save(employeur);
            annonceRepo.save(annonce);
        }
        //verification dans le repo des utilisateur google
        var googleUserExist=utilisateurGoogleRepo.findUtilisateurByAnnonce(idAnnonce).isPresent();
        if (googleUserExist)
        {
            //creation de ma postulation
            var postulation= Postulation.builder()
                    .nom(me.getNom())
                    .prenom(me.getPrenom())
                    .genre(me.getGenre())
                    .adresseMail(me.getAdresseMail())
                    .cv(monCv)
                    .build();
            var employeur = (Employeur) utilisateurGoogleRepo.findUtilisateurByAnnonce(idAnnonce).orElseThrow();
            annonce.getPostulants().add(postulation);
            for (Annonce n:employeur.getAnnonces()) {
                if (n.getId().equals(idAnnonce))
                    n.getPostulants().add(postulation);
            }
//            employeur.getAnnonces().get(employeur.getAnnonces().indexOf(annonce)).getPostulants().add(postulation);
            employeur.getPostulants().add(postulation);
            utilisateurGoogleRepo.save(employeur);
            annonceRepo.save(annonce);
        }

        //si ca ne veut pas s'executer supprimmer cette ligne
    }

    public void deleteFavoris(String idAnnonce) {
        var myAdresseMail=SecurityContextHolder.getContext().getAuthentication().getName();

        var me=utilisateurGoogleRepo
                .findByadresseMail(myAdresseMail)
                .or(()->utilisateurRepo.findByadresseMail(myAdresseMail))
                .orElseThrow();//je cherche dans les repogoogle sinon dans le repo non google

        var annonce=annonceRepo.findById(idAnnonce).orElseThrow();

        ((Employe)me).getFavoris().remove(annonce);

        System.out.println(((Employe)me).getFavoris());

        var isNotGoogleUser= utilisateurRepo.findByadresseMail(myAdresseMail).isPresent();
        var isGoogleUser=utilisateurGoogleRepo.findByadresseMail(myAdresseMail).isPresent();

        if (isGoogleUser){
            System.out.println("je suis un utilisateur google");
            utilisateurGoogleRepo.save(me);
        }
        if (isNotGoogleUser){
            System.out.println("je suis pas un utilisateur google");
            utilisateurRepo.save((Employe)me);
        }


    }

    public ArrayList<Annonce> getPostulations() {
        var myAdresseMail=SecurityContextHolder.getContext().getAuthentication().getName();
        ArrayList<Annonce> postulations= (ArrayList<Annonce>) annonceRepo.findAnnonceByPostulant(myAdresseMail);
        return postulations;
    }
}