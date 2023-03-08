package com.example.khedmabackend.model;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Unwrapped;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.khedmabackend.model.Role.EMPLOYEUR;



@Document

public class Employeur extends Utilisateur implements UserDetails {

    public List<Annonce> getAnnonces() {
        return annonces;
    }

    public void setAnnonces(List<Annonce> annonces) {
        this.annonces = annonces;
    }




    private List<Annonce> annonces=new ArrayList<>();
    public Employeur(String nomUtilisateur, String motDePasse, String adresseMail, String nom, String prenom, Genre genre, String tel, Addresse adresse,Role role) {
        super(nomUtilisateur, motDePasse, adresseMail, nom, prenom, genre, tel, adresse,role);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> EMPLOYEUR.name());
    }

    @Override
    public String getPassword() {
        return this.getMotDePasse();
    }

    @Override
    public String getUsername() {
        return this.getNomUtilisateur();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
