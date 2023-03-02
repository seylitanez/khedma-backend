package com.example.khedmabackend.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Utilisateur {
    @Id
    private String nomUtilisateur;
    private String motDePasse;
    private String adresseMail;
    private String nom;
    private String prenom;
    private Genre genre;
    private String tel;
    private Addresse Wilaya;
    private Utilisateur(){}

}
