package com.example.Authentication.controller;

import com.example.Authentication.Exception.EmailalreadyExist;
import com.example.Authentication.dto.AuthRequest;
import com.example.Authentication.entity.JwtResponse;
import com.example.Authentication.entity.UserCredential;
import com.example.Authentication.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserCredential> addNewUser(@RequestBody @Valid UserCredential userCredential)throws EmailalreadyExist {
        return new ResponseEntity<>(authService.saveUser(userCredential), HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public JwtResponse getToken(@RequestBody AuthRequest authRequest) {
        try{
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            return authService.generateToken(authRequest.getUserName());
        }else{
            throw new RuntimeException("Invalid Access");
        }
        } catch (BadCredentialsException ex) {
                // Handle case where credentials are incorrect
            throw new BadCredentialsException("Invalid Credentials");
            }
    }


    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token){
        authService.validateToken(token);
        String body = "Token is valid";
        return new ResponseEntity<>(body,HttpStatus.OK);
    }

    @GetMapping("/studentDetail")
    public List<UserCredential> studentDetails(){
        return authService.allStudentDetails();
    }



}
