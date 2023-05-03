package com.example.khedmabackend.repo;

import com.example.khedmabackend.model.Utilisateur;
import com.example.khedmabackend.model.UtilisateurGoogle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UtilisateurGoogleRepo extends MongoRepository<UtilisateurGoogle,String> {

    Optional<UtilisateurGoogle> findByadresseMail(String adresseMail);

    void insert(Utilisateur utilisateur);
}
