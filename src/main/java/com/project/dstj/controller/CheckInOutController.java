package com.project.dstj.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.project.dstj.entity.Worker;
import com.project.dstj.entity.Worktime;
import com.project.dstj.service.CheckInOutService;

@RestController
public class CheckInOutController {
    @Autowired
    private CheckInOutService checkInOutService;


    @PostMapping(value = "/check-in", consumes = "application/json")
    public ResponseEntity<Void> checkIn(@RequestHeader("Authorization") String token){
        token = token.substring(7);
        LocalDate worktimeDay = LocalDate.now();
        LocalTime worktimeTime = LocalTime.now();
        Worktime worktime = new Worktime();
        worktime.setWorktimeDay(worktimeDay);
        worktime.setWorktimeStart(worktimeTime);
        worktime.setWorker(checkInOutService.getWorkerPKByToken(token));
        checkInOutService.saveWorktime(worktime);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/check-out", consumes = "application/json")
    public ResponseEntity<Void> checkOut(@RequestHeader("Authorization") String token){
        token = token.substring(7);
        LocalDate worktimeDay = LocalDate.now();
        LocalTime worktimeTime = LocalTime.now();
        Worker worker = checkInOutService.getWorkerPKByToken(token);
        checkInOutService.updateWorktimeEnd(worktimeDay, worker, worktimeTime);
        return ResponseEntity.ok().build();
    }


    // @PostMapping(value = "/check-in", consumes = "application/json")
    // public ResponseEntity<Void> checkIn(@RequestHeader("Authorization") String token,
    // @RequestBody CheckInOutRequest request){
    //     token = token.substring(7);
    //     Worktime worktime = new Worktime();
    //     worktime.setWorktimeDay(request.getWorktimeDay());
    //     worktime.setWorktimeTime(request.getWorktimeTime());
    //     worktime.setWorker(checkInOutService.getWorkerPKByToken(token));
    //     worktime.setWorktimeType("In");
    //     checkInOutService.saveWorktime(worktime);
    //     return ResponseEntity.ok().build();
    // }

    // @PostMapping(value = "/check-out", consumes = "application/json")
    // public ResponseEntity<Void> checkOut(@RequestHeader("Authorization") String token,
    // @RequestBody CheckInOutRequest request){
    //     token = token.substring(7);
    //     Worktime worktime = new Worktime();
    //     worktime.setWorktimeDay(request.getWorktimeDay());
    //     worktime.setWorktimeTime(request.getWorktimeTime());
    //     worktime.setWorker(checkInOutService.getWorkerPKByToken(token));
    //     worktime.setWorktimeType("Out");
    //     checkInOutService.saveWorktime(worktime);
    //     return ResponseEntity.ok().build();
    // }
}