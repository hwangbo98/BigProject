package com.project.dstj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Worker;
import com.project.dstj.repository.AlluserRepository;
import com.project.dstj.repository.PlaceRepository;
import com.project.dstj.repository.WorkerRepository;
import com.project.dstj.security.JwtTokenProvider;
import java.util.Optional;

@Service
public class EditWorkerService {
    private final AlluserRepository allUserRepository;
    private final WorkerRepository workerRepository;


    public EditWorkerService(AlluserRepository allUserRepository, WorkerRepository workerRepository){
        this.allUserRepository = allUserRepository;
        this.workerRepository = workerRepository;
    }

    //workerPK를 기준으로 관련된 alluser를 가져오는 함수
    public Optional<Alluser> findAlluserByWorkerPK(Long workerPK) {
        Optional<Worker> worker = workerRepository.findById(workerPK);
        return worker.map(Worker::getAlluser);
    }


    public void saveWorker(Alluser alluser, Worker worker){
        Alluser savedAlluser = allUserRepository.save(alluser);
        worker.setAlluser(savedAlluser);
        workerRepository.save(worker);
    }
}
