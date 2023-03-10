package com.example.khedmabackend.repo;

import com.example.khedmabackend.model.Utilisateur;
import com.example.khedmabackend.services.ServicePublication;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;
//utilistaeur repository
public interface UtilisateurRepo extends MongoRepository<Utilisateur,String>, ServicePublication {
    Optional<Utilisateur> findBynomUtilisateur(String nomUtilisateur);
    //db.utilisateur.find({"prenom":"sabrine"},{annonces:true})
    List<Utilisateur> findByprenom(String prenom);
}
