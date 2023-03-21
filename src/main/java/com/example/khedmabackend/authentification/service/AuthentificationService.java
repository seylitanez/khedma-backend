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
    //cree une token pour un utilisateur qui se connect
    public ResponseToken Authenticate(AuthentificationRequest authentificationRequest){
        var usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(authentificationRequest.getNomUtilisateur(),authentificationRequest.getMotDePasse());
        Utilisateur utilisateur= utilisateurRepo.findBynomUtilisateur(authentificationRequest.getNomUtilisateur()).orElseThrow();
        var userDetails= userDetailsService.loadUserByUsername(authentificationRequest.getNomUtilisateur());
        String token=jwtService.generateToken((UserDetails) utilisateur);
        System.out.println("token:---->:"+token);
        return ResponseToken.builder().token(token).role(utilisateur.getRole()).build();
    }
    //sauvgarder un nouveux utilisateur et cree son token de conection
    public ResponseToken save(RegisterRequest register) throws Exception {
        System.out.println("save");
        var motDePasse= passwordEncoder.encode(register.getMotDePasse());
        Utilisateur utilisateur = null;
        switch (register.getRole()){
            case EMPLOYE -> {
                System.out.println("employe");
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
                System.out.println("employeur");
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
                System.out.println("moderateur");
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
        return ResponseToken.builder().token(token).role(utilisateur.getRole()).build();
    }
}