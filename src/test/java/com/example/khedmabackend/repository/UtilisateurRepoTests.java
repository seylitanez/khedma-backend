package com.example.khedmabackend.repository;

import com.example.khedmabackend.repo.UtilisateurRepo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@DataMongoTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UtilisateurRepoTests {
    @Autowired
    private UtilisateurRepo utilisateurRepo;

    @Test
    public void find(){
        System.out.println(utilisateurRepo.findAll());
    }
}
