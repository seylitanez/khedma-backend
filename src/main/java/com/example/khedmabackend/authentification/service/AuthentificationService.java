package com.example.khedmabackend.authentification.service;

import com.example.khedmabackend.authentification.AuthentificationRequest;
import com.example.khedmabackend.authentification.RegisterRequest;
import com.example.khedmabackend.authentification.ResponseToken;
import com.example.khedmabackend.config.JwtService;
import com.example.khedmabackend.model.Employe;
import com.example.khedmabackend.model.Employeur;
import com.example.khedmabackend.model.Moderateur;
import com.example.khedmabackend.model.Utilisateur;
import com.example.khedmabackend.repo.UtilisateurRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthentificationService {
    private final UtilisateurRepo utilisateurRepo;
    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;


    public ResponseToken Authenticate(AuthentificationRequest authentificationRequest){

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                new UsernamePasswordAuthenticationToken(authentificationRequest.getNomUtilisateur(),authentificationRequest.getMotDePasse());

        Utilisateur utilisateur= utilisateurRepo.findBynomUtilisateur(authentificationRequest.getNomUtilisateur()).orElseThrow();
        var userDetails= userDetailsService.loadUserByUsername(authentificationRequest.getNomUtilisateur());

        String token=jwtService.generateToken((UserDetails) utilisateur);
        System.out.println("token:---->:"+token);

        return ResponseToken.builder().token(token).build();

    }


    public ResponseToken save(RegisterRequest register) throws Exception {

        var motDePasse= passwordEncoder.encode(register.getMotDePasse());

        Utilisateur utilisateur = null;
        switch (register.getRole()){

            case EMPLOYE -> {
                utilisateurRepo.insert(
                        utilisateur=new Employe(
                                register.getNomUtilisateur(),
                                motDePasse,
                                register.getAdresseMail(),
                                register.getNom(),
                                register.getPrenom(),
                                register.getGenre(),
                                register.getTel(),
                                register.getAdresse(),
                                register.getRole()
                        ));
            }
            case EMPLOYEUR -> {
                utilisateurRepo.insert(
                        utilisateur=new Employeur(
                                register.getNomUtilisateur(),
                                motDePasse,
                                register.getAdresseMail(),
                                register.getNom(),
                                register.getPrenom(),
                                register.getGenre(),
                                register.getTel(),
                                register.getAdresse(),
                                register.getRole()
                        ));
            }
            case MODERATEUR -> {
                utilisateurRepo.insert(
                        utilisateur = new Moderateur(
                                register.getNomUtilisateur(),
                                motDePasse,
                                register.getAdresseMail(),
                                register.getNom(),
                                register.getPrenom(),
                                register.getGenre(),
                                register.getTel(),
                                register.getAdresse(),
                                register.getRole()
                        ));
            }
        }
        if (utilisateur==null)throw new Exception("user null");

        String token=jwtService.generateToken((UserDetails) utilisateur);


        return ResponseToken.builder().token(token).build();

    }

}
