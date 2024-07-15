package com.project.dstj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Place;
import com.project.dstj.entity.Worker;
import com.project.dstj.repository.AlluserRepository;
import com.project.dstj.repository.PlaceRepository;
import com.project.dstj.repository.ServiceRepository;
import com.project.dstj.repository.WorkerRepository;
import com.project.dstj.security.JwtTokenProvider;

@Service
public class AddServiceService {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private AlluserRepository allUserRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private WorkerRepository workerRepository;

    public Place getPlacePKByToken(String token){
        String loginUser = jwtTokenProvider.getUsernameFromJWT(token);
        Alluser user = allUserRepository.findByUsername(loginUser)
        .orElseThrow(() -> new RuntimeException("User not found"));
        Long placePK = user.getPlacePK();
        Place place = placeRepository.findByPlacePK(placePK).orElseThrow(() -> new RuntimeException("Place not found"));
        return place;
    }
    
    public Worker getWorkerPKByWorkerID(String workerId){
        Alluser user = allUserRepository.findByUsername(workerId)
        .orElseThrow(() -> new RuntimeException("User not found"));
        Long userPK = user.getUserPK();
        Worker worker = workerRepository.findByUserPK(userPK).orElseThrow(() -> new RuntimeException("Worker not found"));
        return worker;
    }

    public void saveService(com.project.dstj.entity.Service service){
        serviceRepository.save(service);
    }
}
