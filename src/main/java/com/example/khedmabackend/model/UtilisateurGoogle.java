package com.example.khedmabackend.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UtilisateurGoogle extends Utilisateur implements UserDetails {
    public UtilisateurGoogle(String motDePasse,String adresseMail, String nom, String prenom, Genre genre, String tel, Addresse adresse, Role role) {
        super(motDePasse, adresseMail, nom, prenom, genre, tel, adresse, role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(()->getRole().toString());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return getAdresseMail();
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
