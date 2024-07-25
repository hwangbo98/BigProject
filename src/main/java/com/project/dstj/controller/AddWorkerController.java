package com.project.dstj.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Worker;
import com.project.dstj.entity.Worktime;
import com.project.dstj.request.AddWorkerRequest;
import com.project.dstj.service.AddWorkerService;
import com.project.dstj.service.AlluserService;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.security.crypto.password.PasswordEncoder;



@RestController
public class AddWorkerController {
    private final AddWorkerService addWorkerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AddWorkerController(AddWorkerService addWorkerService){
        this.addWorkerService = addWorkerService;
    }
    
    
    @PostMapping(value = "/add_worker", consumes = "application/json")
    public ResponseEntity<Void> addWorker(@RequestHeader("Authorization") String token,
        @RequestBody AddWorkerRequest request){
        token = token.substring(7);

        Alluser allUser = new Alluser();
        allUser.setUsername(request.getUsername());
        allUser.setPassword(passwordEncoder.encode(request.getPassword()));
        allUser.setUserNickname(request.getUserNickname());
        allUser.setUserAddress(request.getUserAddress());
        allUser.setUserPhoneNumber(request.getUserPhoneNumber());
        allUser.setUserRole("Worker");
        allUser.setPlace(addWorkerService.getPlacePKByToken(token));
        
        Worker worker = new Worker();
        worker.setWorkerSalary(request.getWorkerSalary());

        Worktime worktime = new Worktime();
        worktime.setWorktimeDay(LocalDate.now());
        worktime.setWorktimeStart(LocalTime.now());
        worktime.setWorktimeEnd(LocalTime.now());

        addWorkerService.saveWorker(allUser, worker, worktime);

        return ResponseEntity.ok().build();


    }
    
}
