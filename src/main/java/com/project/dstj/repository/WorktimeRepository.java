package com.project.dstj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.dstj.entity.Worker;
import com.project.dstj.entity.Worktime;

import java.util.List;
import java.util.Optional;

public interface WorktimeRepository extends JpaRepository<Worktime, Long> {
    List<Worktime> findByWorker_Alluser_Place_PlacePK(Long placePK);
    Optional<Worktime> findByWorktimeDayAndWorker(String worktimeDay, Worker worker);
}
