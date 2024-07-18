package com.project.dstj.service;

import com.project.dstj.dto.AttendanceDto;
import com.project.dstj.entity.Attendance;
import com.project.dstj.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    @Transactional(readOnly = true)
    public List<AttendanceDto> getAttendancesByPlaceAndDate(Long placePK, LocalDate date) {
        List<Attendance> attendances = attendanceRepository.findByPlacePKAndDate(placePK, date);

        Map<String, List<Attendance>> groupedByEdu = attendances.stream()
                .collect(Collectors.groupingBy(attendance ->
                        attendance.getMember().getTakes().stream()
                                .map(takes -> takes.getEdu().getEduName())
                                .findFirst().orElse(null)
                ));

        return groupedByEdu.entrySet().stream().map(entry -> {
            String eduName = entry.getKey();
            List<Attendance> eduAttendances = entry.getValue();
            int totalStudents = eduAttendances.size(); // assuming total students = all attendances found
            List<AttendanceDto.AttendanceDetailDto> details = eduAttendances.stream().map(attendance ->
                    AttendanceDto.AttendanceDetailDto.builder()
                            .username(attendance.getMember().getAlluser().getUsername())
                            .memberPK(attendance.getMember().getMemberPK())
                            .attendancePK(attendance.getAttendancePK())
                            .build()
            ).collect(Collectors.toList());

            return AttendanceDto.builder()
                    .date(date)
                    .eduName(eduName)
                    .totalStudents(totalStudents)
                    .attendanceCount(details.size())
                    .attendances(details)
                    .build();
        }).collect(Collectors.toList());
    }
}