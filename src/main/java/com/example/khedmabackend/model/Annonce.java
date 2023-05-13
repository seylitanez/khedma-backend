package com.example.khedmabackend.model;
import com.example.khedmabackend.postulation.Postulation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
@RequiredArgsConstructor
@AllArgsConstructor
//table d'annonce
public class Annonce {
    @Id
    private String id;
    private String nom;
//    private Categorie categorie;
    private String categorie;
    private String sousCategorie;
    private String descriptionFr;
    private String descriptionAr;
    private Addresse adresse;
    private Float salaireDeBase;
    private Jours[] journees=new Jours[7];
    private Date date;
    @Getter
    private List<Postulation> postulants;
}