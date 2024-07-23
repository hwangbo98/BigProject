package com.project.dstj.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Worker;
import com.project.dstj.entity.Worktime;
import com.project.dstj.entity.Place;
import com.project.dstj.repository.AlluserRepository;
import com.project.dstj.repository.PlaceRepository;
import com.project.dstj.repository.WorkerRepository;
import com.project.dstj.repository.WorktimeRepository;
import com.project.dstj.security.JwtTokenProvider;


@Service
public class AddWorkerService {
    private final AlluserRepository allUserRepository;
    private final WorkerRepository workerRepository;

    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private WorktimeRepository worktimeRepository;

    //현재 로그인한 사용자의 토큰으로 placePK알아온뒤, placePK로 place불러옴.
    public Place getPlacePKByToken(String token){
        String loginUser = jwtTokenProvider.getUsernameFromJWT(token);
        Alluser user = allUserRepository.findByUsername(loginUser)
        .orElseThrow(() -> new RuntimeException("User not found"));
        Long placePK = user.getPlacePK();
        Place place = placeRepository.findByPlacePK(placePK).orElseThrow(() -> new RuntimeException("Place not found"));
        return place;
    
    }
    // public List<PlaceDto> getPlacesByToken(String token){
    //     String loginUser = jwtTokenProvider.getUsernameFromJWT(token);
    //     Alluser user = allUserRepository.findByUsername(loginUser)
    //                 .orElseThrow(() -> new RuntimeException("User not found"));
        
    //     Long placePK = user.getPlacePK();
    //     return getPlacesByPlacePK(placePK);
    // }



    // private List<PlaceDto> getPlacesByPlacePK(Long placePK){
    //     List<Place> places = placeRepository.findByPlacePK(placePK);
    //     return places.stream().map(PlaceDto::toDto).collect(Collectors.toList());
    // }

    public AddWorkerService(AlluserRepository allUserRepository, WorkerRepository workerRepository){
        this.allUserRepository = allUserRepository;
        this.workerRepository = workerRepository;
    }

    public void saveWorker(Alluser alluser, Worker worker, Worktime worktime){
        Alluser savedAlluser = allUserRepository.save(alluser);
        worker.setAlluser(savedAlluser);
        Worker savedWorker = workerRepository.save(worker);
        worktime.setWorker(savedWorker);
        worktimeRepository.save(worktime);

    }
}
