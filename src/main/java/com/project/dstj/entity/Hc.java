package com.project.dstj.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

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

    private LocalDateTime hcDate; //측정날짜
}
