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

    // 3. Alluser에서 사용자의 placePK를 파악하는 메서드
    public Long getPlacePKByUsername(String username) {
        return alluserRepository.findByUsername(username)
                .map(alluser -> alluser.getPlace().getPlacePK())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // 4. Alluser, Worker, Edu를 join하고 placePk가 같은 값들만 추출하는 메서드
    public List<EduDto> getEduListByPlacePK(Long placePK) {
        return eduRepository.findEduByPlacePK(placePK).stream()
                .map(edu -> new EduDto(
                        edu.getEduPK(),
                        edu.getEduDay(),
                        edu.getEduEnd(),
                        edu.getEduName(),
                        edu.getEduStart(),
                        edu.getPlace().getPlacePK(),
                        edu.getWorker().getWorkerPK(),
                        edu.getWorker().getAlluser().getUserNickname(),
                        edu.getWorker().getAlluser().getUsername()
                ))
                .collect(Collectors.toList());
    }
}


