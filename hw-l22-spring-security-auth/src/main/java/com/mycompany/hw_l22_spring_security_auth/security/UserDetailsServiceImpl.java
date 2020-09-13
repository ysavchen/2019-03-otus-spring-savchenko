package com.mycompany.hw_l22_spring_security_auth.security;

import com.mycompany.hw_l22_spring_security_auth.domain.AppUser;
import com.mycompany.hw_l22_spring_security_auth.service.AppUserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserDbService appUserDbService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = appUserDbService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email = " + email + " is not found"));

        return User.withUsername(email)
                .password(user.getPassword())
                .authorities(emptyList())
                .build();
    }
}
