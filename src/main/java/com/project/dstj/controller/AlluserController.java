package com.project.dstj.controller;

import com.project.dstj.dto.JwtToken;
import com.project.dstj.dto.AlluserDto;
import com.project.dstj.dto.SignInDto;
import com.project.dstj.dto.SignUpDto;
import com.project.dstj.service.AlluserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AlluserController {
    private final AlluserService alluserService;

    @PostMapping("/sign-in")
    public JwtToken signIn(@RequestBody SignInDto signInDto) {
        String username = signInDto.getUsername();
        String password = signInDto.getPassword();
        JwtToken jwtToken = alluserService.signIn(username, password);
        log.info("request username = {}, password = {}", username, password);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        return jwtToken;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AlluserDto> signUp(@RequestBody SignUpDto signUpDto) {
        AlluserDto savedAlluserDto = alluserService.signUp(signUpDto);
        return ResponseEntity.ok(savedAlluserDto);
    }
    
}
