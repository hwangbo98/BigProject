package com.project.dstj.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendancePK", updatable = false, unique = true, nullable = false)
    private Long attendancePK;

    @ManyToOne
    @JoinColumn(name="memberPK")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member; //회원pk

    private LocalDate createDate;
}
