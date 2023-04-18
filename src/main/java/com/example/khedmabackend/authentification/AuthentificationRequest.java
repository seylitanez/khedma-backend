package com.example.khedmabackend.authentification;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
//table de conection
public class AuthentificationRequest {
    private String adresseMail;
    private String motDePasse;
}
