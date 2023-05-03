package com.example.khedmabackend.model;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.khedmabackend.model.Role.EMPLOYE;

@Document
//table employer
public class Employe extends Utilisateur implements UserDetails {
    private ArrayList<Annonce> Favoris;
    public Employe(String motDePasse, String adresseMail, String nom, String prenom, Genre genre, String tel, Addresse adresse, Role role) {
        super(motDePasse, adresseMail, nom, prenom, genre, tel, adresse, role);
        Favoris=new ArrayList<>();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> EMPLOYE.name());
    }
    @Override
    public String getPassword() {
        return this.getMotDePasse();
    }
    @Override
    public String getUsername() {
        return this.getAdresseMail();
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

    public ArrayList<Annonce> getFavoris() {
        return Favoris;
    }
}
