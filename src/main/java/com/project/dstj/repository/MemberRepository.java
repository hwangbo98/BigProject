package com.project.dstj.repository;

import com.project.dstj.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 가윤 추가
    List<Member> findByMemberPK(Long memberPK);

    // dev 버전
    @Query("SELECT m FROM Member m WHERE m.alluser.userPK = :userPK")
    Optional<Member> findByUserPK(@Param("userPK") Long userPK);
}
