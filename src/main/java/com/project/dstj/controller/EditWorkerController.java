package com.project.dstj.controller;

import com.project.dstj.repository.WorkerRepository;
import com.project.dstj.request.EditWorkerRequest;
import com.project.dstj.service.EditWorkerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Worker;

import java.util.Optional;

@Tag(name = "직원 정보 수정 API", description = "worker list에서 바로 수정")
@RestController
@RequestMapping("/workers")
public class EditWorkerController {
    private final EditWorkerService editWorkerService;
    private final WorkerRepository workerRepository;

    public EditWorkerController(EditWorkerService editWorkerService, WorkerRepository workerRepository){
        this.editWorkerService = editWorkerService;
        this.workerRepository = workerRepository;
    }


    @PutMapping(value = "/edit", consumes = "application/json")
    @Operation(summary = "직원 정보 수정", description = "직원PK, username, salary를 입력받고 수정합니다 (변동하지 않으면 기존 값을 보내야 함)")
    public ResponseEntity<Void> editWorker(@RequestHeader("Authorization") String token,
                                          @RequestBody EditWorkerRequest request){
        token = token.substring(7);
        Optional<Alluser> optionalAllUser = editWorkerService.findAlluserByWorkerPK(request.getWorkerPK());
        Optional<Worker> optionalWorker = workerRepository.findById(request.getWorkerPK());
        if (optionalAllUser.isPresent() && optionalWorker.isPresent()) {
            Alluser allUser = optionalAllUser.get();
            allUser.setUserNickname(request.getUserNickname());
            Worker worker = optionalWorker.get();
            worker.setWorkerSalary(request.getWorkerSalary());
            worker.setAlluser(allUser); // 연관관계 설정

            editWorkerService.saveWorker(allUser, worker);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}

