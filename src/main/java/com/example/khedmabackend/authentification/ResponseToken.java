package com.example.khedmabackend.authentification;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseToken {

    private String token;
}
