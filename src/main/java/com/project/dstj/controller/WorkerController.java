package com.project.dstj.controller;

import com.project.dstj.dto.WorkerListDto;
import com.project.dstj.service.WorkerListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    private WorkerListService workerListService;

    // 직원 관리 페이지
    @GetMapping("/worker-list")
    public List<WorkerListDto> getWorkerList(@RequestHeader("Authorization") String token) {
        // Bearer 토큰 제거
        token = token.substring(7);
        return workerListService.getWorkerListByToken(token);
    }
}
