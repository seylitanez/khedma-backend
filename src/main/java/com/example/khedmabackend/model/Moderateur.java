package com.example.khedmabackend.model;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import static com.example.khedmabackend.model.Role.MODERATEUR;
@Document
//table moderateur
public class Moderateur extends Utilisateur implements  UserDetails {

    private boolean valide;

    public Moderateur(String motDePasse, String adresseMail, String nom, String prenom, Genre genre, String tel, Addresse adresse, Role role,boolean valide) {
        super(motDePasse, adresseMail, nom, prenom, genre, tel, adresse, role,valide);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> MODERATEUR.name());
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