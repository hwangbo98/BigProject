package com.project.dstj.entity;

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
    private Edu edu;

    @ManyToOne
    @JoinColumn(name="memberPK")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @Column(name = "takes_result")
    private String takesResult;
}
