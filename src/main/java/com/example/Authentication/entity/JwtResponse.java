package com.example.Authentication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private Optional<UserCredential> userCredential;
    private String Token;


}
