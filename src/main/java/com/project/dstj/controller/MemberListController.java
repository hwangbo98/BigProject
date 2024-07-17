package com.project.dstj.controller;

import com.project.dstj.dto.MemberListDto;
import com.project.dstj.entity.Alluser;
import com.project.dstj.security.JwtTokenProvider;
import org.springframework.stereotype.Controller;
import com.project.dstj.service.MemberListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberListController {
    // 업체별 회원 리스트 조회
    private final MemberListService memberListService;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberListController(MemberListService memberListService, JwtTokenProvider jwtTokenProvider) {
        this.memberListService = memberListService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/member-list")
    public List<MemberListDto> getMemberList(@RequestHeader("Authorization") String token) {
        // 토큰에서 Bearer 제거
        token = token.substring(7);

        // 토큰에서 사용자명 추출
        String username = jwtTokenProvider.getUsernameFromJWT(token);

        // 사용자 정보 가져오기
        Alluser user = memberListService.findByUsername(username);

        if (user == null) {
            return List.of();
        }

        // 사용자 정보에서 placePK 추출
        Long placePK = user.getPlace().getPlacePK();

        // 회원 리스트 정보 가져오기
        return memberListService.getMemberListByPlacePK(placePK);
    }

}
