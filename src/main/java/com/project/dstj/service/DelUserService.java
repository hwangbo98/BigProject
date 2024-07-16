package com.project.dstj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dstj.repository.AlluserRepository;

@Service
@Transactional
public class DelUserService {
    @Autowired
    private AlluserRepository alluserRepository;

    public void deleteUser(String username){
        alluserRepository.deleteByUsername(username);
    }
}
