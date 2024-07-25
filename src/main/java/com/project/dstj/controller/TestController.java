package com.project.dstj.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dstj.entity.Test;
import com.project.dstj.request.AddTestRequest;
import com.project.dstj.request.DelTestRequest;
import com.project.dstj.service.TestService;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @PostMapping(value = "/add_test", consumes = "application/json")
    public ResponseEntity<Void> addTest(@RequestBody AddTestRequest request){
        LocalDate testDay = LocalDate.now();

        Test test = new Test();
        test.setTestDay(testDay);
        test.setTestName(request.getTestName());
        test.setTestResult(request.getTestResult());
        test.setTakes(testService.getTakesByTakesPK(request.getTakesPK()));
        testService.saveTest(test);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/del_test", consumes = "application/json")
    public ResponseEntity<Void> delTest(@RequestBody DelTestRequest request){
        testService.delTest(request.getTestPK());
        return ResponseEntity.ok().build();
    }
}
