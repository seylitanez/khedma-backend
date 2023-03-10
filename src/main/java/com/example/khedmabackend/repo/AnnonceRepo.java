package com.example.khedmabackend.repo;
import com.example.khedmabackend.model.Annonce;
import org.springframework.data.mongodb.repository.MongoRepository;
//annonce repository
public interface AnnonceRepo extends MongoRepository<Annonce, String> {
    //db.utilisateur.find({"prenom":"sabrine"},{annonces:true})
}
