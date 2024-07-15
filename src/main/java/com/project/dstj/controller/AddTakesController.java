package com.project.dstj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.project.dstj.entity.Takes;
import com.project.dstj.request.AddTakesRequest;
import com.project.dstj.service.AddTakesService;

@RestController
public class AddTakesController {
    @Autowired
    private AddTakesService addTakesService;

    @PostMapping(value = "/add_takes", consumes = "application/json")
    public ResponseEntity<Void> addService(@RequestHeader("Authorization") String token,
    @RequestBody AddTakesRequest request){
        token = token.substring(7);
        Long servicePK = request.getServicePK();
        String memberId = request.getMemberId();
        Takes takes = new Takes();
        takes.setEdu(addTakesService.getEduByPK(servicePK));
        takes.setMember(addTakesService.getMemberPKByMemberID(memberId));
        addTakesService.saveTakes(takes);
        return ResponseEntity.ok().build();
    }
}
