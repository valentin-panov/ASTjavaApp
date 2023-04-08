package com.example.definitelynotvulnerableapp.service;

import com.example.definitelynotvulnerableapp.domain.model.UserData;
import com.example.definitelynotvulnerableapp.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void createUser(UserData userData) {
        UserData existing = userRepository.findUserByName(userData.getName());
        if (existing != null) {
            throw new RuntimeException("User already exists");
        }
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        userRepository.save(userData);
    }

    public UserData getUserByName(String name) {
        return userRepository.findUserByName(name);
    }
}
