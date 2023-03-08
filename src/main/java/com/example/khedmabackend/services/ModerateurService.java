package com.example.khedmabackend.services;

import com.example.khedmabackend.model.Annonce;
import com.example.khedmabackend.repo.AnnonceRepo;
import com.example.khedmabackend.repo.UtilisateurRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ModerateurService {


    private final UtilisateurRepo utilisateurRepo;
    private final AnnonceRepo annonceRepo;


    public List<Annonce> getAnnonces(){

        return annonceRepo.findAll();
    }


}
