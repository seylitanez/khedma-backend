package com.example.khedmabackend.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document
@Data
@RequiredArgsConstructor
@AllArgsConstructor
//table d'annonce
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