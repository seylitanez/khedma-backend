package com.example.khedmabackend.repo;

import com.example.khedmabackend.model.Utilisateur;
import com.example.khedmabackend.services.ServicePublication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
//utilistaeur repository
public interface UtilisateurRepo extends MongoRepository<Utilisateur,String>, ServicePublication {
    Optional<Utilisateur> findByadresseMail(String adresseMail);
    //db.utilisateur.find({"prenom":"sabrine"},{annonces:true})
    List<Utilisateur> findByprenom(String prenom);


    @Query("{'annonces._id': ObjectId(?0)}")
    Optional<Utilisateur> findUtilisateurByAnnonce(String idAnnonce);

}
