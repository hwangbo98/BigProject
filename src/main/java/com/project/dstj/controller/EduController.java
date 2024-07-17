package com.project.dstj.controller;

import com.project.dstj.dto.EduDto;
import com.project.dstj.service.EduInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edu")
public class EduController {
    @Autowired
    private EduInfoService eduInfoService;

    // 토큰을 기반으로 Service 전체를 조회
    @GetMapping("/edu-list")
    public List<EduDto> getEduByToken(@RequestHeader("Authorization") String token) {
        // Bearer 토큰 제거
        token = token.substring(7);
        return eduInfoService.getEduByToken(token);
    }

}

