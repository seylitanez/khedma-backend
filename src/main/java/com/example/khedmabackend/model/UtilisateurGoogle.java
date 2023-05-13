package com.example.khedmabackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Document
public class UtilisateurGoogle  implements UserDetails {

    @Id
    private String id;
    @Indexed(unique = true)
    private String adresseMail;
    private String nom;
    private String prenom;
    private Genre genre;
    private String tel;
    private Addresse adresse;
    private Role role;

    public UtilisateurGoogle(String id, String adresseMail, String nom, String prenom, Genre genre, String tel, Addresse adresse, Role role) {
        this.id = id;
        this.adresseMail = adresseMail;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.tel = tel;
        this.adresse = adresse;
        this.role = role;
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
