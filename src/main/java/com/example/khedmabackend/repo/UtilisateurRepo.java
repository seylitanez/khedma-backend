package com.example.khedmabackend.repo;

import com.example.khedmabackend.model.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UtilisateurRepo extends MongoRepository<Utilisateur,String> {
    Optional<Utilisateur> findBynomUtilisateur(String nomUtilisateur);
}
