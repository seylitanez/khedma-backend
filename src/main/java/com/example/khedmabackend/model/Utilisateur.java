package com.example.khedmabackend.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public abstract class Utilisateur {
    @Id
    private String nomUtilisateur;
    private String motDePasse;
    @Indexed(unique = true)
    private String adresseMail;
    private String nom;
    private String prenom;
    private Genre genre;
    private String tel;
    private Addresse Wilaya;
}
