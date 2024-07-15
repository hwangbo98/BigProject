package com.project.dstj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.dstj.entity.Place;
import java.util.Optional;


public interface PlaceRepository extends JpaRepository <Place, Long>{
    Optional<Place> findByPlacePK(Long placePK);
}