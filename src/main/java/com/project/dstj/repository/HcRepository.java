package com.project.dstj.repository;

import com.project.dstj.entity.Hc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HcRepository extends JpaRepository<Hc, Long> {
    List<Hc> findByMemberAlluserUserPK(Long userPK);
}
