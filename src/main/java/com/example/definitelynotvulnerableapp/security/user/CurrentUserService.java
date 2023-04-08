package com.example.definitelynotvulnerableapp.security.user;

import com.example.definitelynotvulnerableapp.domain.model.UserData;
import com.example.definitelynotvulnerableapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserService {
    private final UserService userService;

    public UserData getCurrentUserData() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUserByName(name);
    }
}
