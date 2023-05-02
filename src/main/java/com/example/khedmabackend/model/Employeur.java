package com.example.khedmabackend.model;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.khedmabackend.model.Role.EMPLOYEUR;
@Document
//table employeur
public class Employeur extends Utilisateur implements UserDetails {
    private String entreprise;
    private List<Annonce> annonces=new ArrayList<>();
    public Employeur(String motDePasse, String adresseMail, String nom, String prenom, Genre genre, String tel, Addresse adresse, Role role, String entreprise) {
        super(motDePasse, adresseMail, nom, prenom, genre, tel, adresse, role);
        this.entreprise = entreprise;
    }
    public List<Annonce> getAnnonces() {
        return annonces;
    }
    public void setAnnonces(List<Annonce> annonces) {
        this.annonces = annonces;
    }
//    public Employeur(String motDePasse, String adresseMail, String nom, String prenom, Genre genre, String tel, Addresse adresse, Role role) {
//        super(motDePasse, adresseMail, nom, prenom, genre, tel, adresse, role);
//        this.annonces = annonces;
//    }
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
}
