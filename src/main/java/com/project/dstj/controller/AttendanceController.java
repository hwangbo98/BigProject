package com.project.dstj.controller;

import com.project.dstj.dto.AttendanceDto;
import com.project.dstj.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @GetMapping("/all")
    public ResponseEntity<List<AttendanceDto>> getAllAttendances(
            @RequestParam Long placePK) {
        LocalDate today = LocalDate.now();
        List<AttendanceDto> attendanceDtos = attendanceService.getAttendancesByPlaceAndDate(placePK, today);
        return ResponseEntity.ok(attendanceDtos);
    }
}