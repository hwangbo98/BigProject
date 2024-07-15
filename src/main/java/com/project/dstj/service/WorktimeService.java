package com.project.dstj.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dstj.entity.Worker;
import com.project.dstj.entity.Worktime;
import com.project.dstj.repository.WorktimeRepository;

@Service
public class WorktimeService {
    @Autowired
    private WorktimeRepository worktimeRepository;
    public void updateWorktimeEnd(String worktimeDay, Worker worker, String worktimeEnd){
        Optional<Worktime> worktimeOptional = worktimeRepository.findByWorktimeDayAndWorker(worktimeDay, worker);
        worktimeOptional.ifPresent(worktime -> {
            worktime.setWorktimeEnd(worktimeEnd);
            worktimeRepository.save(worktime);
        });
    }
}
