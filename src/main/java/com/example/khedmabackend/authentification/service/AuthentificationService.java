package com.example.khedmabackend.authentification.service;

import com.example.khedmabackend.authentification.*;
import com.example.khedmabackend.config.JwtService;
import com.example.khedmabackend.model.*;
import com.example.khedmabackend.repo.UtilisateurGoogleRepo;
import com.example.khedmabackend.repo.UtilisateurRepo;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import static com.example.khedmabackend.Utils.Constantes.*;
@RequiredArgsConstructor
@Service
public class AuthentificationService {
    private final UtilisateurRepo utilisateurRepo;
    private final UtilisateurGoogleRepo utilisateurGoogleRepo;
    private final JwtService jwtService;
//    private final JwtServiceGoogle jwtServiceGoogle;
    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder PasswordEncoder;
    //cree une token pour un utilisateur qui se connect
    public ResponseToken authenticate(AuthentificationRequest authentificationRequest) {
        try {


            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authentificationRequest.getAdresseMail(), authentificationRequest.getMotDePasse());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            System.out.println(authentificationRequest.getAdresseMail());
            Utilisateur utilisateur = utilisateurRepo.findByadresseMail(authentificationRequest.getAdresseMail()).orElseThrow();
            var userDetails = userDetailsService.loadUserByUsername(authentificationRequest.getAdresseMail());
            String token = jwtService.generateToken(userDetails, utilisateur);
            System.out.println(GREEN + "token:---->:" + token);
            return ResponseToken.builder().token(token).build();
        } catch (AuthenticationException e) {
            throw new IllegalStateException("invalid username or password");
        }
    }
    public ResponseToken authenticateGoogle(AuthentificationRequestGoogle authentificationRequestGoogle) throws Exception {

        var googleToken=authentificationRequestGoogle.getToken();//je recupere le token que ma genere le frontend

        var email=jwtService.extractEmail(googleToken);

        var utilisateur=utilisateurGoogleRepo.findByEmail(email);

        var userDetails= userDetailsService.loadUserByUsername(email);

        String token=jwtService.generateToken(userDetails,utilisateur);

        try{
            return ResponseToken.builder().token(token).build();
        }catch (Exception e){
            throw new Exception("invalid token");
        }

    }
    //sauvgarder un nouveux utilisateur et cree son token de conection
    public ResponseToken save(RegisterRequest register) throws Exception {
//        IOUtils.copy(file.getInputStream(),new FileOutputStream("cv.png"));
        System.out.println("save");
        var motDePasse= PasswordEncoder.encode(register.getMotDePasse());
        var adresseMailExist=
                utilisateurRepo.
                findByadresseMail(register.getAdresseMail())
                        .isPresent();

        if (adresseMailExist) throw new IllegalStateException("user already exist");

        Utilisateur utilisateur = null;
        switch (register.getRole()){
            case EMPLOYE -> {
                System.out.println(PURPLE+"employe");
                utilisateurRepo.insert(
                        utilisateur=new Employe(
                                motDePasse,
                                register.getAdresseMail(),
                                register.getNom(),
                                register.getPrenom(),
                                register.getGenre(),
                                register.getTel(),
                                register.getAdresse(),
                                register.getRole(),
                                false
                        ));
            }
            case EMPLOYEUR -> {
                System.out.println(PURPLE+"employeur");
                utilisateurRepo.insert(
                        utilisateur=new Employeur(
                                motDePasse,
                                register.getAdresseMail(),
                                register.getNom(),
                                register.getPrenom(),
                                register.getGenre(),
                                register.getTel(),
                                register.getAdresse(),
                                register.getRole(),
                                register.getEntreprise(),
                                false
                        ));
            }
            case MODERATEUR -> {
                System.out.println(PURPLE+"moderateur");
                utilisateurRepo.insert(
                        utilisateur = new Moderateur(
                                motDePasse,
                                register.getAdresseMail(),
                                register.getNom(),
                                register.getPrenom(),
                                register.getGenre(),
                                register.getTel(),
                                register.getAdresse(),
                                register.getRole(),
                                true
                        ));
            }
        }

        if (utilisateur==null)throw new Exception(RED+"user null");
        String token=jwtService.generateToken((UserDetails) utilisateur,utilisateur);
        return ResponseToken.builder().token(token).build();
    }



    public ResponseToken saveGoogleUser(RegisterRequest register) throws Exception {

        System.out.println("save google");


        var adresseMailExist=
                utilisateurRepo.
                        findByadresseMail(register.getAdresseMail())
                        .isPresent()
                        ||
                        utilisateurGoogleRepo.
                                findByadresseMail(register.getAdresseMail())
                                .isPresent();


        if (adresseMailExist) throw new Exception("user already exist");

        UtilisateurGoogle utilisateur = null;
        switch (register.getRole()){
            case EMPLOYE -> {
                System.out.println(PURPLE+"employe");
                utilisateurGoogleRepo.insert(
                        utilisateur=new Employe(
                                null,
                                register.getAdresseMail(),
                                register.getNom(),
                                register.getPrenom(),
                                register.getGenre(),
                                register.getTel(),
                                register.getAdresse(),
                                register.getRole(),
                                true//on valide son compte car on a pas besoin de la verification par mail vu que c'est un google user
                        ));
            }
            case EMPLOYEUR -> {
                System.out.println(PURPLE+"employeur");
                utilisateurGoogleRepo.insert(
                        utilisateur=new Employeur(
                                null,
                                register.getAdresseMail(),
                                register.getNom(),
                                register.getPrenom(),
                                register.getGenre(),
                                register.getTel(),
                                register.getAdresse(),
                                register.getRole(),
                                register.getEntreprise(),
                                true
                        ));
            }
            case MODERATEUR -> {
                System.out.println(PURPLE+"moderateur");
                utilisateurGoogleRepo.insert(
                        utilisateur = new Moderateur(
                                null,
                                register.getAdresseMail(),
                                register.getNom(),
                                register.getPrenom(),
                                register.getGenre(),
                                register.getTel(),
                                register.getAdresse(),
                                register.getRole(),
                                true
                        ));
            }
        }
        if (utilisateur==null)throw new Exception(RED+"user null");
        String token=jwtService.generateToken((UserDetails) utilisateur,utilisateur);
        return ResponseToken.builder().token(token).build();
    }
}