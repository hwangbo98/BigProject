package com.project.dstj.repository;

import com.project.dstj.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByTakes_Member_Alluser_Place_PlacePKAndTakes_Member_MemberPK(Long placePK, Long memberPK);




}
