package com.project.dstj.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servicePK", updatable = false, unique = true, nullable = false)
    private Long servicePK;

    @ManyToOne
    @JoinColumn(name="workerPK")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Worker worker; //직원pk //담당직원

    @ManyToOne
    @JoinColumn(name="placePK")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Place place;

    private String serviceName; //서비스이름

    private String serviceDay; //서비스요일

    private String serviceStart; //시작시간

    private String serviceEnd; //끝시간
}
