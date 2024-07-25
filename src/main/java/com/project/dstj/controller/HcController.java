package com.project.dstj.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dstj.entity.Hc;
import com.project.dstj.entity.Member;
import com.project.dstj.entity.Test;
import com.project.dstj.repository.MemberRepository;
import com.project.dstj.request.AddHcRequest;
import com.project.dstj.service.HcService;

@RestController
public class HcController {
    @Autowired
    private HcService hcService;

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping(value = "/add_hc", consumes = "application/json")
    public ResponseEntity<Void> addTest(@RequestBody AddHcRequest request){
        Hc hc = new Hc();
        hc.setHcSex(request.getHcSex());
        hc.setHcJob(request.getHcJob());
        hc.setHcHeight(request.getHcHeight());
        hc.setHcWeight(request.getHcWeight());
        LocalDate hcDate = LocalDate.now();
        hc.setHcDate(hcDate);
        hc.setHcPurpose(request.getHcPurpose());
        hc.setHcTotalbodywater(request.getHcTotalbodywater());
        hc.setHcProtein(request.getHcTotalbodywater());
        hc.setHcMinerals(request.getHcMinerals());
        hc.setHcBodyfatmass(request.getHcBodyfatmass());
        hc.setHcSkeletalmusclemass(request.getHcSkeletalmusclemass());
        List<Member> memberList = memberRepository.findByMemberPK(request.getMemberPK());
        for (Member member : memberList) {
            hc.setMember(member);
        }
        hcService.saveHc(hc);
        return ResponseEntity.ok().build();
    }


}
