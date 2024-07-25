package com.project.dstj.repository;

import com.project.dstj.entity.Ac;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcRepository extends JpaRepository<Ac, Long> {
    List<Ac> findByMemberAlluserUserPK(Long userPK);
}
