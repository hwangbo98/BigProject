package com.project.dstj.service;

import com.project.dstj.entity.Edu;
import com.project.dstj.repository.EduRepository;
import org.springframework.stereotype.Service;

@Service
public class EditEduService {
    private final EduRepository eduRepository;


    public EditEduService(EduRepository eduRepository){
        this.eduRepository = eduRepository;
    }

    public void saveEdu(Edu edu){
        eduRepository.save(edu);
    }
}
