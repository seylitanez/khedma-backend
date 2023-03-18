package com.example.khedmabackend.repo;

import com.example.khedmabackend.model.Annonce;
import com.example.khedmabackend.model.Utilisateur;
import com.example.khedmabackend.services.ServicePublication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepo extends MongoRepository<Utilisateur,String>, ServicePublication {
    Optional<Utilisateur> findBynomUtilisateur(String nomUtilisateur);


    List<Utilisateur> findByprenom(String prenom);
}
