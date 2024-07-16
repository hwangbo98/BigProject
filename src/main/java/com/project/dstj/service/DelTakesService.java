package com.project.dstj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dstj.entity.Edu;
import com.project.dstj.entity.Member;
import com.project.dstj.repository.TakesRepository;

@Service
@Transactional
public class DelTakesService {
    @Autowired
    private TakesRepository takesRepository;

    public void deleteTakes(Edu edu, Member member){
        takesRepository.deleteByEduAndMember(edu, member);
    }
}
