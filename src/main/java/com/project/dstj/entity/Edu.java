package com.project.dstj.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Edu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eduPK", updatable = false, unique = true, nullable = false)
    private Long eduPK;

    @OneToOne
    @JoinColumn(name="workerPK")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Worker worker; //직원pk //담당직원

    @ManyToOne
    @JoinColumn(name="placePK")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Place place;

    private String eduName; //서비스이름

    private String eduDay; //서비스요일

    private String eduStart; //시작시간

    private String eduEnd; //끝시간
}