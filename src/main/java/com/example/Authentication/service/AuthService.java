package com.example.Authentication.service;

import com.example.Authentication.Exception.EmailalreadyExist;
import com.example.Authentication.entity.JwtResponse;
import com.example.Authentication.entity.UserCredential;
import com.example.Authentication.repository.UserCredentialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    UserCredentialRepo userCredentialRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    public UserCredential saveUser(UserCredential userCredential)throws EmailalreadyExist {
        Optional<UserCredential> checkUserEmail = userCredentialRepo.findByEmail(userCredential.getEmail());
        System.out.println(checkUserEmail);
        if(checkUserEmail.isPresent()){
            throw new EmailalreadyExist("This email already register = "+userCredential.getEmail());
        }else {
            userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
            return userCredentialRepo.save(userCredential);
        }
    }

    public JwtResponse generateToken(String userName){
        String token = jwtService.generateToken(userName);
        Optional<UserCredential>user = userCredentialRepo.findByEmail(userName);
        return new JwtResponse(user,token);

    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    public List<UserCredential> allStudentDetails(){
        return userCredentialRepo.findAll();
    }
}
