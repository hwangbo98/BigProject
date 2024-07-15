package com.project.dstj.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Worker;
import com.project.dstj.entity.Worktime;
import com.project.dstj.repository.AlluserRepository;
import com.project.dstj.repository.WorkerRepository;
import com.project.dstj.repository.WorktimeRepository;
import com.project.dstj.security.JwtTokenProvider;

@Service
public class CheckInOutService {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AlluserRepository allUserRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private WorktimeRepository worktimeRepository;

    public Worker getWorkerPKByToken(String token){
        String loginUser = jwtTokenProvider.getUsernameFromJWT(token);
        Alluser user = allUserRepository.findByUsername(loginUser)
        .orElseThrow(() -> new RuntimeException("User not found"));
        Long userPK = user.getUserPK();
        Worker worker = workerRepository.findByUserPK(userPK).orElseThrow(() -> new RuntimeException("Worker not found"));
        return worker;
    }

    public void updateWorktimeEnd(LocalDate worktimeDay, Worker worker, LocalTime worktimeEnd){
        Optional<Worktime> worktimeOptional = worktimeRepository.findByWorktimeDayAndWorker(worktimeDay, worker);
        worktimeOptional.ifPresent(workTime -> {
            workTime.setWorktimeEnd(worktimeEnd);
            worktimeRepository.save(workTime);
        });
    }

    public void saveWorktime(Worktime worktime){
        worktimeRepository.save(worktime);
    }
}