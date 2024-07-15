package com.project.dstj.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.dstj.entity.Worker;;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

}
