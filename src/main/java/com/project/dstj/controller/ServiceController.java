package com.project.dstj.controller;

import com.project.dstj.dto.ServiceDto;
import com.project.dstj.service.ServiceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {
    @Autowired
    private ServiceInfoService serviceInfoService;

    // 토큰을 기반으로 Service 전체를 조회
    @GetMapping("/service-list")
    public List<ServiceDto> getServicesByToken(@RequestHeader("Authorization") String token) {
        // Bearer 토큰 제거
        token = token.substring(7);
        return serviceInfoService.getServicesByToken(token);
    }

    // 새로운 Service 엔티티를 생성
    @PostMapping
    public ServiceDto createService(@RequestBody ServiceDto serviceDto) {
        return serviceInfoService.createService(serviceDto);
    }
}
