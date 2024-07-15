package com.project.dstj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.dstj.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m FROM Member m WHERE m.alluser.userPK = :userPK")
    Optional<Member> findByUserPK(@Param("userPK") Long userPK);
}
