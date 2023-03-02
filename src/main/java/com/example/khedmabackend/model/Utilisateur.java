package com.example.khedmabackend.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document

public class Utilisateur {

    @Id
    private String id;
    @Indexed(unique = true)
    private String nomUtilisateur;
    private String motDePasse;
    private String adresseMail;
    private String nom;
    private String prenom;
    private Genre genre;
    private String tel;
    private Addresse adresse;
    private Role role;

    public Utilisateur(String nomUtilisateur, String motDePasse, String adresseMail, String nom, String prenom, Genre genre, String tel, Addresse wilaya,Role role) {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
        this.adresseMail = adresseMail;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.tel = tel;
        this.adresse = adresse;
        this.role=role;
    }
}
