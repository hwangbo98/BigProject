package com.project.dstj.repository;

import com.project.dstj.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByMemberPK(Long memberPK);
}
