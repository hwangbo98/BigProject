package com.project.dstj.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Member;
import com.project.dstj.request.AddMemberRequest;
import com.project.dstj.s3.S3Upload;
import com.project.dstj.service.AddMemberService;

@RestController
public class AddMemberController {
    @Autowired
    private AddMemberService addMemberService;

    @Autowired
    private S3Upload s3Upload;

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

    @PostMapping("/add_member_img")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "imgurl") MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        String fileName = multipartFile.getOriginalFilename();
        long fileSize = multipartFile.getSize();

        String url = s3Upload.upload(multipartFile);
        return ResponseEntity.ok().build();
    }
}
