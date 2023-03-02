package com.example.khedmabackend.repo;

import com.example.khedmabackend.model.Annonce;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnnonceRepo extends MongoRepository<Annonce,String> {
}
