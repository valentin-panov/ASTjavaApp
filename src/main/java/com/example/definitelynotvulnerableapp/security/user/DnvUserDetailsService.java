package com.example.definitelynotvulnerableapp.security.user;

import com.example.definitelynotvulnerableapp.domain.model.UserData;
import com.example.definitelynotvulnerableapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DnvUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserData userData = userService.getUserByName(username);
        if (userData == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
                .username(userData.getName())
                .password(userData.getPassword())
                .authorities(userData.getRole())
                .build();
    }
}
