package com.project.dstj.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.dstj.entity.Edu;

public interface EduRepository extends JpaRepository <Edu, Long>{
    List<Edu> findByPlace_PlacePK(Long placePK);
    Optional<Edu> findByEduPK(Long eduPK);
}
