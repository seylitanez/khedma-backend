package com.example.khedmabackend.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document
//table utilistaeur
public abstract class Utilisateur extends UtilisateurGoogle implements UserDetails {

    @Getter
    private String motDePasse;

    @Getter@Setter
    private Boolean valide;

    public Utilisateur(String motDePasse, String adresseMail, String nom, String prenom, Genre genre, String tel, Addresse adresse, Role role, Boolean valide) {
        super(motDePasse, adresseMail, nom, prenom, genre, tel, adresse, role);
        this.motDePasse = motDePasse;
        this.valide=valide;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(()->getRole().toString());
    }

    @Override
    public String getPassword() {
        System.out.println(getMotDePasse());
        return getMotDePasse();
    }

    @Override
    public String getUsername() {
        return getAdresseMail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}