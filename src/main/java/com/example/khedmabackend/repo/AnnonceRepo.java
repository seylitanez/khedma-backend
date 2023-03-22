package com.example.khedmabackend.repo;
import com.example.khedmabackend.model.Annonce;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

//annonce repository
public interface AnnonceRepo extends MongoRepository<Annonce, String> {

    // requet personalisé il va chercher si le mot cles dans nom de l'annonce et la categorie et la description
    @Query("{$or: [ { nom: { $regex: ?0 } }, { categorie: { $regex: ?0 } }, { descriptionFr: { $regex: ?0 } }]}")
    public List<Annonce> searchAnnonces(String motCle);
}
