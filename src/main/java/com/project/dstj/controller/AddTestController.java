package com.project.dstj.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dstj.entity.Test;
import com.project.dstj.request.AddTestRequest;
import com.project.dstj.service.AddTestService;

@RestController
public class AddTestController {
    @Autowired
    private AddTestService addTestService;

    @PostMapping(value = "/add_test", consumes = "application/json")
    public ResponseEntity<Void> addTest(@RequestBody AddTestRequest request){
        LocalDate testDay = LocalDate.now();

        Test test = new Test();
        test.setTestDay(testDay);
        test.setTestName(request.getTestName());
        test.setTestResult(request.getTestResult());
        test.setTakes(addTestService.getTakesByTakesPK(request.getTakesPK()));
        addTestService.saveTest(test);
        return ResponseEntity.ok().build();
    }
}
