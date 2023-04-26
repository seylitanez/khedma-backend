package com.example.khedmabackend.authentification;

import com.example.khedmabackend.model.Addresse;
import com.example.khedmabackend.model.Genre;
import com.example.khedmabackend.model.Role;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
//table d'inscription
public class RegisterRequest {
    private String motDePasse;
    private String adresseMail;
    private String nom;
    private String prenom;
    private Genre genre;
    private String tel;
    private Addresse adresse;
    private Role role;
    private String entreprise;
}
