package com.project.dstj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.project.dstj.entity.Edu;
import com.project.dstj.request.AddEduRequest;
import com.project.dstj.service.AddEduService;

@RestController
public class AddEduController {
    @Autowired
    private AddEduService addEduService;

    @PostMapping(value = "/add_edu", consumes = "application/json")
    public ResponseEntity<Void> addService(@RequestHeader("Authorization") String token,
    @RequestBody AddEduRequest request){
        token = token.substring(7);
        Edu edu = new Edu();
        edu.setEduName(request.getEduName());
        edu.setEduDay(request.getEduDay());
        edu.setEduStart(request.getEduStart());
        edu.setEduEnd(request.getEduEnd());
        edu.setEduTuition(request.getEduTuition());
        edu.setPlace(addEduService.getPlacePKByToken(token));
        edu.setWorker(addEduService.getWorkerPKByWorkerID(request.getWorkerId()));

        addEduService.saveEdu(edu);

        return ResponseEntity.ok().build();
    }
}
