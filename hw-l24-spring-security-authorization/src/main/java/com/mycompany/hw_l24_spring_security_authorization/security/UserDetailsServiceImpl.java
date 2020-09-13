package com.mycompany.hw_l24_spring_security_authorization.security;

import com.mycompany.hw_l24_spring_security_authorization.domain.AppUser;
import com.mycompany.hw_l24_spring_security_authorization.service.AppUserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserDbService appUserDbService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = appUserDbService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email = " + email + " is not found"));

        return User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("ROLE_USER")
                .build();
    }
}
