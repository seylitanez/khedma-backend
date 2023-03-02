package com.example.khedmabackend.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Builder
@Document
@Data
@RequiredArgsConstructor
public class Annonce {

    @Id
    private String id;
    private String nom;
    private Categorie categorie;
    private String sousCategorie;
    private Float salaireDeBase;
    private Jours[] journees=new Jours[7];
    private Date date;

}
