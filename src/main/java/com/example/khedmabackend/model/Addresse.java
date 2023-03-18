package com.example.khedmabackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Addresse {
    private String wilaya;
    private String commune;

}
