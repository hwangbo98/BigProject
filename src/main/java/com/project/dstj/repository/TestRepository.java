package com.project.dstj.repository;

import com.project.dstj.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByTakes_Member_Alluser_Place_PlacePKAndTakes_Member_MemberPK(Long placePK, Long memberPK);




=======

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByTakesMemberAlluserUserPK(Long userPK);
>>>>>>> 3966cad48f9c50af961c9cb3c0e96e00e1b49016
}
