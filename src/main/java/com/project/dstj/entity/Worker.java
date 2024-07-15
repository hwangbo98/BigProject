package com.project.dstj.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Getter
@Setter
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workerPK", updatable = false, unique = true, nullable = false)
    private Long workerPK;

    @ManyToOne
    @JoinColumn(name="userPK")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Alluser alluser; //유저pk

    @Column(name = "workerSalary")
    private Integer workerSalary; //직원급여

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Edu> edus;
}
