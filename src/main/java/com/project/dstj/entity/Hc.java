package com.project.dstj.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
public class Hc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hcPK", updatable = false, unique = true, nullable = false)
    private Long hcPK;

    @ManyToOne
    @JoinColumn(name="memberPK")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member; //회원pk

    private Float hcHeight; //키

    private Float hcWeight; //몸무게

    private LocalDate hcDate; //측정날짜

    private String hcPurpose; //운동목적

    private Float hcTotalbodywater; //체수분

    private Float hcProtein; //단백질

    private Float hcMinerals; //무기질

    private Float hcBodyfatmass; //체지방

    private Float hcSkeletalmusclemass; //골격근량

    private String hcReport; //ai리포트 결과 조언
}
