package com.project.dstj.repository;

import com.project.dstj.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT a FROM Attendance a WHERE a.member.alluser.place.placePK = :placePK AND a.createDate = :date")
    List<Attendance> findByPlacePKAndDate(@Param("placePK") Long placePK, @Param("date") LocalDate date);
}