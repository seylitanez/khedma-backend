package com.example.khedmabackend.postulation;

import com.example.khedmabackend.model.Addresse;
import com.example.khedmabackend.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Postulation {
    private String adresseMail;
    private String nom;
    private String prenom;
    private Genre genre;
    private String tel;
    private Addresse adresse;
    private String cv;

}
