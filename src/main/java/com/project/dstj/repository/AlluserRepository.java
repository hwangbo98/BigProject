package com.project.dstj.repository;

import com.project.dstj.entity.Alluser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlluserRepository extends JpaRepository<Alluser, Long> {
    Optional<Alluser> findByUsername(String username);
    boolean existsByUsername(String username);
}
