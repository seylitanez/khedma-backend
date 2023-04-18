package com.example.khedmabackend.authentification;


import com.example.khedmabackend.model.Role;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
//table de token
public class ResponseToken {
    private String token;
}