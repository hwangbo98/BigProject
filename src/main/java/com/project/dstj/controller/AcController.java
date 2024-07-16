package com.project.dstj.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dstj.entity.Ac;
import com.project.dstj.entity.Member;
import com.project.dstj.repository.MemberRepository;
import com.project.dstj.request.AddAcRequest;
import com.project.dstj.service.AcService;

@RestController
public class AcController {
    @Autowired
    private AcService acService;

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping(value = "/add_ac", consumes = "application/json")
    public ResponseEntity<Void> addTest(@RequestBody AddAcRequest request){
        Ac ac = new Ac();
        ac.setAcGrade(request.getAcGrade());
        ac.setAcSchool(request.getAcSchool());
        ac.setAcClass(request.getAcClass());
        ac.setAcParent(request.getAcParent());
        LocalDate acDate = LocalDate.now();
        ac.setAcDate(acDate);
        List<Member> memberList = memberRepository.findByMemberPK(request.getMemberPK());
        for (Member member : memberList) {
            ac.setMember(member);
        }
        acService.saveAc(ac);
        return ResponseEntity.ok().build();
    }
}
