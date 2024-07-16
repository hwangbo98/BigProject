package com.project.dstj.dto;

import com.project.dstj.entity.Test;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class TestListDto {
    private String userNickname;
    private String eduName;
    private LocalDate testDay;
    private String testResult;
    private String testName;

    // Entity -> DTO
    public static TestListDto toDto(Test test) {
        TestListDto dto = new TestListDto();
        dto.setUserNickname(test.getTakes().getMember().getAlluser().getUserNickname());
        dto.setEduName(test.getTakes().getEdu().getEduName());
        dto.setTestDay(test.getTestDay());
        dto.setTestResult(test.getTestResult());
        dto.setTestName(test.getTestName());
        return dto;
    }

    public Test toEntity() {
        Test test = new Test();
        test.setTestDay(this.testDay);
        test.setTestResult(this.testResult);
        test.setTestName(this.testName);
        return test;
    }
}
