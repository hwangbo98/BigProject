package com.project.dstj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dstj.entity.Ac;
import com.project.dstj.repository.AcRepository;

@Service
@Transactional
public class AcService {
    @Autowired
    private AcRepository acRepository;

    public void saveAc(Ac ac){
        acRepository.save(ac);
    }
}
