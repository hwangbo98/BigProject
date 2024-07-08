package com.project.dstj.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "placePK", updatable = false, unique = true, nullable = false)
    private Long placePK;

    @Column(name = "placeName")
    private String placeName; //업체명

    @Column(name = "placeType")
    private String placeType; //업종
}
