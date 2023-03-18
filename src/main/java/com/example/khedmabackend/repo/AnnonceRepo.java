package com.example.khedmabackend.repo;

import com.example.khedmabackend.model.Annonce;
import com.example.khedmabackend.services.ServicePublication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AnnonceRepo extends MongoRepository<Annonce, String> {

}
