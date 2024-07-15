package com.project.dstj.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project.dstj.dto.PlaceDto;
import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Worker;
import com.project.dstj.entity.Place;
import com.project.dstj.request.AddWorkerRequest;
import com.project.dstj.security.JwtTokenProvider;
import com.project.dstj.service.AddWorkerService;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class AddWorkerController {
    private final AddWorkerService addWorkerService;

    public AddWorkerController(AddWorkerService addWorkerService){
        this.addWorkerService = addWorkerService;
    }

    @PostMapping(value = "/add_worker", consumes = "application/json")
    public ResponseEntity<Void> addWorker(@RequestHeader("Authorization") String token,
        @RequestBody AddWorkerRequest request){
        token = token.substring(7);

        Alluser allUser = new Alluser();
        allUser.setUsername(request.getUsername());
        allUser.setPassword(request.getPassword());
        allUser.setUserNickname(request.getUserNickname());
        allUser.setUserAddress(request.getUserAddress());
        allUser.setUserPhoneNumber(request.getUserPhoneNumber());
        allUser.setUserRole("worker");
        allUser.setPlace(addWorkerService.getPlacePKByToken(token));
        
        Worker worker = new Worker();
        worker.setWorkerSalary(request.getWorkerSalary());

        addWorkerService.saveWorker(allUser, worker);

        return ResponseEntity.ok().build();


    }
    
}
