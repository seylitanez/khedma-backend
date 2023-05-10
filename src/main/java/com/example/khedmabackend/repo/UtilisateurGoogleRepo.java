package com.example.khedmabackend.repo;

import com.example.khedmabackend.model.Utilisateur;
import com.example.khedmabackend.model.UtilisateurGoogle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UtilisateurGoogleRepo extends MongoRepository<UtilisateurGoogle,String> {

    Optional<UtilisateurGoogle> findByadresseMail(String adresseMail);


//    @Query("")
//    Optional<Utilisateur> gfindByadresseMail(String adresseMail);
    @Query("{ 'adresseMail' : ?0 }")
    UtilisateurGoogle findByEmail(String email);

//    void save(Utilisateur utilisateur);


    void insert(Utilisateur utilisateur);

    @Query("{'annonces._id': ObjectId(?0)}")
    Optional<UtilisateurGoogle> findUtilisateurByAnnonce(String idAnnonce);
}
