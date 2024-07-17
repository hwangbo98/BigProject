package com.project.dstj.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberPK", updatable = false, unique = true, nullable = false)
    private Long memberPK;

    @ManyToOne
    @JoinColumn(name="userPK")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Alluser alluser; //유저pk

    private String significant; //빈칸
}
