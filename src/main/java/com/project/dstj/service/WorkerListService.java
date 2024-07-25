package com.project.dstj.service;

import com.project.dstj.dto.WorkerListDto;
import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Worktime;
import com.project.dstj.repository.WorktimeRepository;
import com.project.dstj.repository.AlluserRepository;
import com.project.dstj.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkerListService {

    @Autowired
    private WorktimeRepository worktimeRepository;

    @Autowired
    private AlluserRepository alluserRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public List<WorkerListDto> getWorkerListByToken(String token) {
        String username = jwtTokenProvider.getUsernameFromJWT(token); // 토큰에서 사용자 이름 추출
        Alluser user = alluserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User 찾을수 없음")); // 사용자 이름으로 Alluser 엔티티 조회

        if (!user.getUserRole().equalsIgnoreCase("Master")) { //UserRole이 Master와 같아야함
            throw new RuntimeException("Unauthorized access");
        }

        Long placePK = user.getPlace().getPlacePK(); // Alluser 엔티티에서 placePK 추출
        return getWorkerListByPlacePK(placePK); // placePK로 Worker 정보 리스트 조회
    }

    private List<WorkerListDto> getWorkerListByPlacePK(Long placePK) {
        List<Worktime> worktimes = worktimeRepository.findByWorker_Alluser_Place_PlacePK(placePK);
        return worktimes.stream().map(WorkerListDto::toDto).collect(Collectors.toList());
    }
}
