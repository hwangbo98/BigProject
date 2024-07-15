package com.project.dstj.repository;

import com.project.dstj.entity.Edu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EduRepository extends JpaRepository<Edu, Long> {
    // dev 버전
    List<Edu> findByPlace_PlacePK(Long placePK);

    // 가윤 추가
    Optional<Edu> findByEduPK(Long eduPK);
}
