package com.project.dstj.controller;

import com.project.dstj.entity.Member;
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
    public ApiResponse<List<MemberDto>> getMembersByEduPk(@PathVariable Long eduPk) {
        log.info("getMembersByEduPk {}", eduPk);
        List<Member> members = eduInquiryService.getMembersByEduPk(eduPk);
        List<MemberDto> memberDtos = members.stream()
                .map(member -> new MemberDto(member.getMemberPK(), member.getAlluser().getUsername()))
                .collect(Collectors.toList());
        return new ApiResponse<>(memberDtos);
    }

    public static class MemberDto {
        private Long memberPk;
        private String username;

        public MemberDto(Long memberPk, String username) {
            this.memberPk = memberPk;
            this.username = username;
        }

        // Getters and Setters
        public Long getMemberPk() {
            return memberPk;
        }

        public void setMemberPk(Long memberPk) {
            this.memberPk = memberPk;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
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
