package com.mycompany.hw_l25_spring_batch.domain.rdb;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "airports")
@NoArgsConstructor
public class AirportRdb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "airport_name", nullable = false)
    private String name;

    @Column(name = "city")
    private String city;

}
