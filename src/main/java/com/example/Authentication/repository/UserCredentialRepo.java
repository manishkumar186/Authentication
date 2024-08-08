package com.example.Authentication.repository;

import com.example.Authentication.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepo extends JpaRepository<UserCredential,Integer> {
    Optional<UserCredential> findByEmail(String userName);
}
