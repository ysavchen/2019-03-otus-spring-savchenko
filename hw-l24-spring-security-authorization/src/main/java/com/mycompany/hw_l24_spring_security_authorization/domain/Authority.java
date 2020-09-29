package com.mycompany.hw_l24_spring_security_authorization.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "role", nullable = false)
    private String role;

    public Authority() {
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
