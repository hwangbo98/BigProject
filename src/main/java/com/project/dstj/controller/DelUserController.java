package com.project.dstj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.project.dstj.entity.Alluser;
import com.project.dstj.repository.AlluserRepository;
import com.project.dstj.request.DelUserRequest;
import com.project.dstj.security.JwtTokenProvider;
import com.project.dstj.service.DelUserService;

@RestController
public class DelUserController {
    @Autowired
    private DelUserService delUserService;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AlluserRepository alluserRepository;

    
    @PostMapping(value = "/del_user", consumes = "application/json")
    public ResponseEntity<Void> delUser(@RequestHeader("Authorization") String token){
        token = token.substring(7);
        String loginUser = jwtTokenProvider.getUsernameFromJWT(token);
        Alluser user = alluserRepository.findByUsername(loginUser)
        .orElseThrow(() -> new RuntimeException("User not found"));
        delUserService.deleteUser(user.getUsername());
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/del_member", consumes = "application/json")
    public ResponseEntity<Void> delMember(@RequestBody DelUserRequest request){
        String username = request.getUsername();
        delUserService.deleteUser(username);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/del_worker", consumes = "application/json")
    public ResponseEntity<Void> delWorker(@RequestBody DelUserRequest request){
        String username = request.getUsername();
        delUserService.deleteUser(username);
        return ResponseEntity.ok().build();
    }
}
