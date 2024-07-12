package com.project.dstj.repository;

import com.project.dstj.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository <Service, Long> {
    List<Service> findByPlace_PlacePK(Long placePK);

}
