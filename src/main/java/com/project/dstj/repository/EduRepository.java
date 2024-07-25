package com.project.dstj.repository;

import com.project.dstj.dto.EduDto;
import com.project.dstj.entity.Edu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EduRepository extends JpaRepository<Edu, Long> {
    // dev 버전
    List<Edu> findByPlace_PlacePK(Long placePK);

    // 가윤 추가
    Optional<Edu> findByEduPK(Long eduPK);


    @Query("SELECT e FROM Edu e JOIN e.place p JOIN e.worker w WHERE p.placePK = :placePK")
    List<Edu> findEduByPlacePK(Long placePK); //edu-list -> placePK별
}
