package com.project.dstj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AttendanceDto {
    private LocalDate date;
    private String eduName;
    private int totalStudents;
    private int attendanceCount;
    private List<AttendanceDetailDto> attendances;

    @Data
    @Builder
    @AllArgsConstructor
    public static class AttendanceDetailDto {
        private String username;
        private Long memberPK;
        private Long attendancePK;
    }
}