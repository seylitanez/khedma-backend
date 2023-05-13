package com.example.khedmabackend.controller;

import com.example.khedmabackend.authentification.service.AuthentificationService;
import com.example.khedmabackend.config.JwtService;
import com.example.khedmabackend.repo.UtilisateurGoogleRepo;
import com.example.khedmabackend.repo.UtilisateurRepo;
import com.example.khedmabackend.services.EmailService;
import com.example.khedmabackend.services.ServiceConfirmation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.InvalidKeyException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test for the class AuthenticationController")
class AuthenticationControllerTest {
    @Mock
    private AuthentificationService authentificationService;
    @Mock
    private ServiceConfirmation serviceConfirmation;
    @Mock
    private EmailService emailService;
    @Mock
    private UtilisateurGoogleRepo utilisateurGoogleRepo;
    @Mock
    private UtilisateurRepo utilisateurRepo;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthenticationController controller;

    @Test
    @DisplayName("Should confirm the user when the token is valid")
    void confirmWhenTokenIsValid() throws InvalidKeyException {
        String validToken = "validToken";
        doNothing().when(serviceConfirmation).confirm(validToken);

        assertDoesNotThrow(() -> controller.confirm(validToken));

        verify(serviceConfirmation, times(1)).confirm(validToken);
    }

    @Test
    @DisplayName("Should throw an exception when the token is invalid")
    void confirmWhenTokenIsInvalidThenThrowException() throws InvalidKeyException {
        String invalidToken = "invalidToken";
        doThrow(InvalidKeyException.class).when(serviceConfirmation).confirm(invalidToken);

        assertThrows(InvalidKeyException.class, () -> controller.confirm(invalidToken));

        verify(serviceConfirmation, times(1)).confirm(invalidToken);
    }
}