package com.project.dstj.repository;

import com.project.dstj.entity.Takes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TakesRepository extends JpaRepository<Takes, Long> {
    List<Takes> findByEduEduPK(Long eduPK);
}
