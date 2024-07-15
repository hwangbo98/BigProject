package com.project.dstj.entity;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private LocalDate worktimeDay; //날짜

    // @Column(name = "worktimeTime")
    // private String worktimeTime;

    
    @Column(name = "worktimeStart")
    private LocalTime worktimeStart; //출근시간

    
    @Column(name = "worktimeEnd")
    private LocalTime worktimeEnd; //퇴근시간

    // @Column(name = "worktimeType")
    // private String worktimeType; //출근이냐? 퇴근이냐?

}