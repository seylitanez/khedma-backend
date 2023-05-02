package com.example.khedmabackend.services;


import com.example.khedmabackend.config.JwtService;
import com.example.khedmabackend.repo.UtilisateurRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;

@Service
@RequiredArgsConstructor
public class ServiceConfirmation {

    private final JwtService jwtService;

    private final UtilisateurRepo utilisateurRepo;

    public void confirm(String token) throws InvalidKeyException {
        boolean isValid=
                jwtService
                        .isTokenValid(token);

        if (!isValid)throw new InvalidKeyException("invalid token");

        String addresseMail= jwtService.extractAddresseMail(token);
        var account= utilisateurRepo.findByadresseMail(addresseMail).orElseThrow();
        account.setValide(true);
        utilisateurRepo.save(account);

    }

}
