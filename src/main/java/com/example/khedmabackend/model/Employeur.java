package com.example.khedmabackend.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static com.example.khedmabackend.Utils.Constantes.EMPLOYEUR;


@Document
public class Employeur extends Utilisateur /*implements UserDetails */{
    public Employeur(String nomUtilisateur, String motDePasse, String adresseMail, String nom, String prenom, Genre genre, String tel, Addresse wilaya) {
        super(nomUtilisateur, motDePasse, adresseMail, nom, prenom, genre, tel, wilaya);
    }
    //
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(() -> EMPLOYEUR);
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
}
