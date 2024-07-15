package com.project.dstj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Member;
import com.project.dstj.request.AddMemberRequest;
import com.project.dstj.service.AddMemberService;

@RestController
public class AddMemberController {
    @Autowired
    private AddMemberService addMemberService;

    @PostMapping(value = "/add_member", consumes = "application/json")
    public ResponseEntity<Void> addMember(@RequestHeader("Authorization") String token,
    @RequestBody AddMemberRequest request){
        token = token.substring(7);

        Alluser allUser = new Alluser();
        allUser.setUsername(request.getUsername());
        allUser.setPassword(request.getPassword());
        allUser.setUserNickname(request.getUserNickname());
        allUser.setUserAddress(request.getUserAddress());
        allUser.setUserPhoneNumber(request.getUserPhoneNumber());
        allUser.setUserRole("member");
        allUser.setPlace(addMemberService.getPlacePKByToken(token));

        Member member = new Member();

        addMemberService.saveMember(allUser, member);

        return ResponseEntity.ok().build();
    }
}
