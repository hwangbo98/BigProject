package com.project.dstj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.project.dstj.entity.Edu;
import com.project.dstj.repository.EduRepository;
import com.project.dstj.request.DelEduRequest;


@RestController
public class DelEduController {

    @Autowired
    private EduRepository eduRepository;

    @PostMapping(value = "/del_edu", consumes = "application/json")
    public ResponseEntity<Void> delEdu(@RequestHeader("Authorization") String token,
    @RequestBody DelEduRequest request){
        Long eduPK = request.getEduPK();
        System.out.println(eduPK);
        System.out.println(eduPK);
        System.out.println(eduPK);
        System.out.println(eduPK);
        Edu edu = eduRepository.findByEduPK(eduPK).orElseThrow(() -> new RuntimeException("Edu not found"));
        eduRepository.delete(edu);

        return ResponseEntity.ok().build();
    
    }
}
