package com.project.dstj.controller;

import com.project.dstj.entity.Edu;
import com.project.dstj.repository.EduRepository;
import com.project.dstj.request.EditEduRequest;
import com.project.dstj.service.EditEduService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "수업 정보 수정 API(EditEduController)", description = "수업 정보 리스트창에서 요청")
@RestController
public class EditEduController {
    private final EditEduService editEduService;
    private final EduRepository eduRepository;

    public EditEduController(EditEduService editEduService, EduRepository eduRepository){
        this.editEduService = editEduService;
        this.eduRepository = eduRepository;
    }

    @PutMapping(value = "/edit/edu", consumes = "application/json")
    @Operation(summary = "수업 정보 수정", description = "수업PK, 수업명, 날짜, 시작시간, 끝시간을 입력받고 수정합니다 (변동하지 않으면 기존 값을 보내야 함)")
    public ResponseEntity<Void> editEdu(@RequestHeader("Authorization") String token,
                                           @RequestBody EditEduRequest request){



        token = token.substring(7);
        Optional<Edu> optionalEdu = eduRepository.findById(request.getEduPK());
        if (optionalEdu.isPresent()) {
            Edu edu = optionalEdu.get();
            edu.setEduName(request.getEduName());
            edu.setEduDay(request.getEduDay());
            edu.setEduStart(request.getEduStart());
            edu.setEduEnd(request.getEduEnd()); // 연관관계 설정

            editEduService.saveEdu(edu);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
