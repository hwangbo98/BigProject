package com.project.dstj.controller;

import com.project.dstj.dto.EduDto;

import com.project.dstj.security.JwtTokenProvider;

import com.project.dstj.service.EduInfoService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/edu")
public class EduController {

    private final EduInfoService eduInfoService;
    private final JwtTokenProvider jwtTokenProvider;


    @GetMapping("/edu-list")
    public ResponseEntity<List<EduDto>> getEduList(@RequestHeader("Authorization") String token) {

        String username = jwtTokenProvider.getUsernameFromJWT(token.substring(7));


        Long placePK = eduInfoService.getPlacePKByUsername(username);

        List<EduDto> eduList = eduInfoService.getEduListByPlacePK(placePK);

        return ResponseEntity.ok(eduList);
    }
}
