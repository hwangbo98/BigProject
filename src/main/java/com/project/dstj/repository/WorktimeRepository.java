package com.project.dstj.repository;

import com.project.dstj.entity.Worktime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorktimeRepository extends JpaRepository<Worktime, Long> {
    List<Worktime> findByWorker_Alluser_Place_PlacePK(Long placePK);
}
