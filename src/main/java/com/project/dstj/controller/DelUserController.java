package com.project.dstj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dstj.request.DelUserRequest;
import com.project.dstj.service.DelUserService;

@RestController
public class DelUserController {
    @Autowired
    private DelUserService delUserService;
    
    @PostMapping(value = "/del_user", consumes = "application/json")
    public ResponseEntity<Void> delUser(@RequestBody DelUserRequest request){
        String username = request.getUsername();
        delUserService.deleteUser(username);
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
