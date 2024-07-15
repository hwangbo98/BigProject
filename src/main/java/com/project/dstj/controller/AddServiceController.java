package com.project.dstj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.project.dstj.entity.Service;
import com.project.dstj.request.AddServiceRequest;
import com.project.dstj.service.AddServiceService;

@RestController
public class AddServiceController {
    
    @Autowired
    private AddServiceService addServiceService;

    @PostMapping(value = "/add_service", consumes = "application/json")
    public ResponseEntity<Void> addService(@RequestHeader("Authorization") String token,
    @RequestBody AddServiceRequest request){
        token = token.substring(7);
        Service service = new Service();
        service.setServiceName(request.getServiceName());
        service.setServiceDay(request.getServiceDay());
        service.setServiceStart(request.getServiceStart());
        service.setServiceEnd(request.getServiceEnd());
        service.setPlace(addServiceService.getPlacePKByToken(token));
        service.setWorker(addServiceService.getWorkerPKByWorkerID(request.getWorkerId()));

        addServiceService.saveService(service);

        return ResponseEntity.ok().build();
    }
}
