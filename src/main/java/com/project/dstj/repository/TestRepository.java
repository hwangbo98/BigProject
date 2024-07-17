package com.project.dstj.repository;

import com.project.dstj.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByTakesMemberAlluserUserPK(Long userPK);
    List<Test> findByTakes_Member_Alluser_Place_PlacePKAndTakes_Member_MemberPK(Long placePK, Long memberPK);
    void deleteByTestPK(Long testPK);

}
