package com.mycompany.hw_l28_spring_integration.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "airports")
@NoArgsConstructor
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "airport_name", nullable = false)
    private String name;

    @Column(name = "city")
    private String city;

}
