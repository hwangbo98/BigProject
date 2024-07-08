package com.project.dstj.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
public class Ac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acPK", updatable = false, unique = true, nullable = false)
    private Long acPK;

    @ManyToOne
    @JoinColumn(name="memberPK")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member; //회원pk

    private String acGrade; //학년

    private String acSchool; //학교

    private String acClass; //소속반

    private String acParent; //학부모번호

    private LocalDateTime acDate; //가입일

    private String acSignificant; //특이사항
}
