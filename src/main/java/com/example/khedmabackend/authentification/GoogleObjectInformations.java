package com.example.khedmabackend.authentification;

import lombok.Data;

@Data
public class GoogleObjectInformations {
    private String email;
    private String familyName;
    private String givenName;
    private String googleId;
    private String imageUrl;
    private String name;


}