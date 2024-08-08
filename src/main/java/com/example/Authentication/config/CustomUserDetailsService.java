package com.example.Authentication.config;

import com.example.Authentication.entity.UserCredential;
import com.example.Authentication.repository.UserCredentialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserCredentialRepo userCredentialRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> credential = userCredentialRepo.findByEmail(username);
        return credential.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("user not found with name" + username));
    }
}
