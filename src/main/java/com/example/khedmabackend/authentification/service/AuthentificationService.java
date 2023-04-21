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
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.khedmabackend.Utils.Constantes.*;
@RequiredArgsConstructor
@Service
public class AuthentificationService {
    private final UtilisateurRepo utilisateurRepo;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;
    //cree une token pour un utilisateur qui se connect
    public ResponseToken Authenticate(AuthentificationRequest authentificationRequest){
        var usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(authentificationRequest.getAdresseMail(),authentificationRequest.getMotDePasse());
        Utilisateur utilisateur= utilisateurRepo.findByadresseMail(authentificationRequest.getAdresseMail()).orElseThrow();
        System.out.println(utilisateur);
        var userDetails= userDetailsService.loadUserByUsername(authentificationRequest.getAdresseMail());
        String token=jwtService.generateToken((UserDetails) utilisateur,utilisateur);
        System.out.println(GREEN+"token:---->:"+token);
        return ResponseToken.builder().token(token).build();
    }




    //sauvgarder un nouveux utilisateur et cree son token de conection
    public ResponseToken save(RegisterRequest register) throws Exception {
//        IOUtils.copy(file.getInputStream(),new FileOutputStream("cv.png"));
        System.out.println("save");
        var motDePasse= passwordEncoder.encode(register.getMotDePasse());
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
                                register.getRole()
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
                                register.getRole()
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
                                register.getRole()
                        ));
            }
        }
        if (utilisateur==null)throw new Exception(RED+"user null");
        String token=jwtService.generateToken((UserDetails) utilisateur,utilisateur);
        return ResponseToken.builder().token(token).build();
    }
}