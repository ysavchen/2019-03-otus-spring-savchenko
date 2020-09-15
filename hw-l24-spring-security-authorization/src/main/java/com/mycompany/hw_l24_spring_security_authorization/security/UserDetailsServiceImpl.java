package com.mycompany.hw_l24_spring_security_authorization.security;

import com.mycompany.hw_l24_spring_security_authorization.service.UserDbService;
import com.mycompany.hw_l24_spring_security_authorization.service.AuthorityDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDbService userDbService;
    private final AuthorityDbService authorityDbService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userDbService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email = " + email + " is not found"));

        List<GrantedAuthority> authorities = authorityDbService.findAuthoritiesByUserId(user.getId())
                .stream()
                .map(GrantedAuthority.class::cast)
                .collect(toList());

        return User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
