package com.project.dstj.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Edu;
import com.project.dstj.entity.Member;
import com.project.dstj.entity.Takes;
import com.project.dstj.repository.AlluserRepository;
import com.project.dstj.repository.EduRepository;
import com.project.dstj.repository.MemberRepository;
import com.project.dstj.repository.TakesRepository;

@org.springframework.stereotype.Service
public class AddTakesService {

    @Autowired
    private EduRepository eduRepository;

    @Autowired
    private TakesRepository takesRepository;

    @Autowired
    private AlluserRepository alluserRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Edu getEduByPK(Long eduPK){
        Edu edu = eduRepository.findByEduPK(eduPK).orElseThrow(() -> new RuntimeException("Edu not found"));
        return edu;
    }

    public Member getMemberPKByMemberID(String memberId){
        Alluser user = alluserRepository.findByUsername(memberId)
        .orElseThrow(() -> new RuntimeException("User not found"));
        Long userPK = user.getUserPK();
        Member member = memberRepository.findByUserPK(userPK).orElseThrow(() -> new RuntimeException("Member not found"));
        return member;

    }

    public void saveTakes(Takes takes){
        takesRepository.save(takes);
    }
}
