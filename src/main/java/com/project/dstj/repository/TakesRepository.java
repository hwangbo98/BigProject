package com.project.dstj.repository;

import com.project.dstj.entity.Edu;
import com.project.dstj.entity.Member;
import com.project.dstj.entity.Takes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TakesRepository extends JpaRepository<Takes, Long> {
    List<Takes> findByEduEduPK(Long eduPK);
    void deleteByEduAndMember(Edu edu, Member member);
    Optional<Takes> findByTakesPK(Long takesPK);
}
