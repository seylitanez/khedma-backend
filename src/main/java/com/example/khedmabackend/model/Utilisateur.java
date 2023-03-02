package com.example.khedmabackend.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public abstract class Utilisateur {
    @Id
    private String id;
    @Indexed(unique = true)
    private String nomUtilisateur;
    private String motDePasse;
    @Indexed(unique = true)
    private String adresseMail;
    private String nom;
    private String prenom;
    private Genre genre;
    private String tel;
    private Addresse Wilaya;

    public Utilisateur(String nomUtilisateur, String motDePasse, String adresseMail, String nom, String prenom, Genre genre, String tel, Addresse wilaya) {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
        this.adresseMail = adresseMail;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.tel = tel;
        Wilaya = wilaya;
    }
}
