package com.project.dstj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dstj.entity.Takes;
import com.project.dstj.entity.Test;
import com.project.dstj.repository.TakesRepository;
import com.project.dstj.repository.TestRepository;

@Service
@Transactional
public class TestService {
    @Autowired
    private TakesRepository takesRepository;

    @Autowired
    private TestRepository testRepository;

    public Takes getTakesByTakesPK(Long takesPK){
        Takes takes = takesRepository.findByTakesPK(takesPK).orElseThrow(() -> new RuntimeException("Takes not found"));
        return takes;
    }

    public void delTest(Long testPK){
        testRepository.deleteByTestPK(testPK);
    }

    public void saveTest(Test test){
        testRepository.save(test);
    }
}
