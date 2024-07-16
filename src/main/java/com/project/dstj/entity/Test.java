package com.project.dstj.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testPK", updatable = false, unique = true, nullable = false)
    private Long testPK;

    @ManyToOne
    @JoinColumn(name="takesPK")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Takes takes; //takes PK

    @Column(name = "testDay")
    private String testDay; //시험날짜

    @Column(name = "testName")
    private String testName; //시험이름

    @Column(name = "testResult")
    private String testResult; //시험결과
}
