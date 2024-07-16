package com.project.dstj.repository;

import com.project.dstj.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByTakesMemberAlluserUserPK(Long userPK);
    void deleteByTestPK(Long testPK);
}
