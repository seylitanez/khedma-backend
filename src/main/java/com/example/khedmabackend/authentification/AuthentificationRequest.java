package com.example.khedmabackend.authentification;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthentificationRequest {

    private String nomUtilisateur;
    private String motDePasse;

}
