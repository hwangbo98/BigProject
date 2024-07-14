package com.project.dstj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.dstj.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
