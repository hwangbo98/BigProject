package com.project.dstj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dstj.entity.Hc;
import com.project.dstj.repository.HcRepository;

@Service
@Transactional
public class HcService {
    @Autowired
    private HcRepository hcRepository;

    public void saveHc(Hc hc){
        hcRepository.save(hc);
    }
}
