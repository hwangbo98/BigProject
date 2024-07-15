package com.project.dstj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.project.dstj.entity.Worktime;
import com.project.dstj.request.CheckInOutRequest;
import com.project.dstj.service.CheckInOutService;

@RestController
public class CheckInOutController {
    @Autowired
    private CheckInOutService checkInOutService;

    @PostMapping(value = "/check-in", consumes = "application/json")
    public ResponseEntity<Void> checkIn(@RequestHeader("Authorization") String token,
    @RequestBody CheckInOutRequest request){
        token = token.substring(7);
        Worktime worktime = new Worktime();
        worktime.setWorktimeDay(request.getWorktimeDay());
        worktime.setWorktimeTime(request.getWorktimeTime());
        worktime.setWorker(checkInOutService.getWorkerPKByToken(token));
        worktime.setWorktimeType("In");
        checkInOutService.saveWorktime(worktime);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/check-out", consumes = "application/json")
    public ResponseEntity<Void> checkOut(@RequestHeader("Authorization") String token,
    @RequestBody CheckInOutRequest request){
        token = token.substring(7);
        Worktime worktime = new Worktime();
        worktime.setWorktimeDay(request.getWorktimeDay());
        worktime.setWorktimeTime(request.getWorktimeTime());
        worktime.setWorker(checkInOutService.getWorkerPKByToken(token));
        worktime.setWorktimeType("Out");
        checkInOutService.saveWorktime(worktime);
        return ResponseEntity.ok().build();
    }
}