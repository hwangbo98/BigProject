package com.project.dstj.service;


import com.project.dstj.dto.EduDto;
import com.project.dstj.repository.AlluserRepository;
import com.project.dstj.repository.EduRepository;
import com.project.dstj.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EduInfoService {

    private final AlluserRepository alluserRepository;
    private final EduRepository eduRepository;
    private final WorkerRepository workerRepository;

    public Long getPlacePKByUsername(String username) {
        return alluserRepository.findByUsername(username)
                .map(alluser -> alluser.getPlace().getPlacePK())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<EduDto> getEduListByPlacePK(Long placePK) {
        return eduRepository.findEduByPlacePK(placePK).stream()
                .map(EduDto::toDto)
                .collect(Collectors.toList());
    }
}


