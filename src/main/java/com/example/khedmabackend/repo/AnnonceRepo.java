package com.example.khedmabackend.repo;
import com.example.khedmabackend.model.Annonce;
import com.example.khedmabackend.model.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

//annonce repository
public interface AnnonceRepo extends MongoRepository<Annonce, String> {

    // requet personalisé il va chercher si le mot cles dans nom de l'annonce et la categorie et la description
    @Query("{$or: [ { nom: { $regex: ?0 , $options: 'i' } },{ 'adresse.wilaya' : {$regex:?0, $options: 'i'}},{ 'adresse.commune' : {$regex:?0, $options: 'i'}}, { categorie: { $regex: ?0 , $options: 'i'} }, { descriptionFr: { $regex: ?0  , $options: 'i'} },{ descriptionAr: { $regex: ?0 , $options: 'i'} }]}")
    public List<Annonce> searchAnnonces(String motCle);

    Optional<Annonce> findAnnonceByid(String id);

    //recupres les annonces ou je suis postulant
    @Query("{'postulants.adresseMail': ?0}")
    List<Annonce> findAnnonceByPostulant(String adresseMail);
}
