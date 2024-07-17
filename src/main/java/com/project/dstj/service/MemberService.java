package com.project.dstj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dstj.entity.Member;
import com.project.dstj.repository.MemberRepository;

@Service
@Transactional
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public void saveHc(Member member){
        memberRepository.save(member);
    }
}
