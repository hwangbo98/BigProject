package com.project.dstj.entity;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Takes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "takesPK", updatable = false, unique = true, nullable = false)
    private Long takesPK;


    @ManyToOne
    @JoinColumn(name="eduPK")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Edu edu; //서비스pk

    @ManyToOne
    @JoinColumn(name="memberPK")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member; //회원pk

    // @Column(name = "takesResult")
    // private String takesResult;

    // @OneToMany(mappedBy = "takes", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Test> tests;
}
