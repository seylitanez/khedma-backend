package com.example.khedmabackend.repo;

import com.example.khedmabackend.model.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UtilisateurRepo extends MongoRepository<Utilisateur,String> {

}
