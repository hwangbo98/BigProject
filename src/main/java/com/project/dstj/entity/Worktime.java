package com.project.dstj.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Worktime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WorktimePK", updatable = false, unique = true, nullable = false)
    private Long worktimePK;

    @ManyToOne
    @JoinColumn(name="workerPK")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Worker worker; //직원pk

    @Column(name = "worktimeDay")
    private String worktimeDay; //날짜

    @Column(name = "worktimeStart")
    private String worktimeStart; //출근시간

    @Column(name = "worktimeEnd")
    private String worktimeEnd; //퇴근시간

}
