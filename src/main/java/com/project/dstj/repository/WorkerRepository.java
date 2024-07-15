package com.project.dstj.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.dstj.entity.Worker;;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    @Query("SELECT w FROM Worker w WHERE w.alluser.userPK = :userPK")
    Optional<Worker> findByUserPK(@Param("userPK") Long userPK);
}
