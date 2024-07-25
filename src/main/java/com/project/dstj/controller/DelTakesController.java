package com.project.dstj.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Edu;
import com.project.dstj.entity.Member;
import com.project.dstj.repository.AlluserRepository;
import com.project.dstj.repository.EduRepository;
import com.project.dstj.repository.MemberRepository;
import com.project.dstj.request.DelTakesRequest;
import com.project.dstj.service.DelTakesService;


@RestController
public class DelTakesController {

    @Autowired
    private AlluserRepository alluserRepository;

    @Autowired
    private EduRepository eduRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DelTakesService delTakesService;

    @PostMapping(value = "/del_takes", consumes = "application/json")
    public ResponseEntity<Void> delTakes(@RequestBody DelTakesRequest request){
        String memberId = request.getMemberId();
        
        Alluser alluser = alluserRepository.findByUsername(memberId).orElseThrow(() -> new RuntimeException("User not found"));
        Long userPK = alluser.getUserPK();
        Edu edu = eduRepository.findByEduPK(request.getEduPK()).orElseThrow(() -> new RuntimeException("User not found"));
        Member member = memberRepository.findByUserPK(userPK).orElseThrow(() -> new RuntimeException("Member not found"));

        delTakesService.deleteTakes(edu, member);
        return ResponseEntity.ok().build();
    }
}
