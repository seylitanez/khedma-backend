package com.example.khedmabackend.authentification;

import lombok.Data;

@Data
public class AuthentificationRequestGoogle {
    private GoogleObjectInformations googleUserObj;
    private Long exp;


}
