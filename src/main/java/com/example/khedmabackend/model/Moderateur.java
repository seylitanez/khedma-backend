package com.example.khedmabackend.model;

import com.example.khedmabackend.services.Administrateur;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static com.example.khedmabackend.model.Role.MODERATEUR;


@Document

public class Moderateur extends Utilisateur implements Administrateur , UserDetails {


    public Moderateur(String nomUtilisateur, String motDePasse, String adresseMail, String nom, String prenom, Genre genre, String tel, Addresse wilaya, Role role) {
        super(nomUtilisateur, motDePasse, adresseMail, nom, prenom, genre, tel, wilaya,role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> MODERATEUR.name());
    }

    @Override
    public String getPassword() {
        return getPassword();
    }

    @Override
    public String getUsername() {
        return getUsername();
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

    @Override
    public void supprimerAnnonce() {

    }
}
