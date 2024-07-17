package com.project.dstj.controller;

import com.project.dstj.dto.EduMemInfoDto;
import com.project.dstj.entity.Takes;
import com.project.dstj.service.EduInquiryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/edu")
@Slf4j
public class EduInquireController {

    @Autowired
    private EduInquiryService eduInquiryService;

    @GetMapping("/{eduPk}/members")
    public ApiResponse<List<EduMemInfoDto>> getMembersByEduPk(@PathVariable Long eduPk) {
        log.info("getMembersByEduPk {}", eduPk);
        List<Takes> takesList = eduInquiryService.getTakesByEduPk(eduPk);
        List<EduMemInfoDto> memberDtos = takesList.stream()
                .map(takes -> new EduMemInfoDto(
                        takes.getMember().getAlluser().getUserPK(),
                        takes.getMember().getAlluser().getUsername(),
                        takes.getTakesPK()))
                .collect(Collectors.toList());
        return new ApiResponse<>(memberDtos);
    }

    public static class ApiResponse<T> {
        private T data;

        public ApiResponse(T data) {
            this.data = data;
        }

        // Getters and Setters
        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
}
