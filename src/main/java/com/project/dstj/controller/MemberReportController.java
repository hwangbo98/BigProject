package com.project.dstj.controller;

import com.project.dstj.dto.TestListDto;
import com.project.dstj.entity.Alluser;
import com.project.dstj.service.TestListService;
import com.project.dstj.security.JwtTokenProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberReportController {
    private final TestListService testListService;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberReportController(TestListService testListService, JwtTokenProvider jwtTokenProvider) {
        this.testListService = testListService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/members/report") // 개인리포트
    public List<TestListDto> getMemberReport(@RequestHeader("Authorization") String token) {
        // 토큰에서 Bearer 제거
        token = token.substring(7);

        // 토큰에서 사용자명 추출
        String username = jwtTokenProvider.getUsernameFromJWT(token);

        // 사용자 정보 가져오기
        Alluser user = testListService.findByUsername(username);

        if (user == null) {
            return List.of();
        }

        // 사용자 정보에서 placePK와 memberPK 추출
        Long placePK = user.getPlace().getPlacePK();
        Long memberPK = user.getMembers().stream().findFirst().get().getMemberPK();

        // 보고서 정보 가져오기
        return testListService.getMemberReportByPlacePkAndMemberPk(placePK, memberPK);
    }
}
