package com.project.dstj.dto;

import com.project.dstj.entity.Attendance;
import com.project.dstj.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MemberListDto {
    private String userNickname;
    private LocalDate createDate;
    private String username;

    // Entity -> DTO
    public static MemberListDto toDto(Member member, Attendance attendance) {
        MemberListDto dto = new MemberListDto();
        dto.setUserNickname(member.getAlluser().getUserNickname());
        dto.setCreateDate(attendance != null ? attendance.getCreateDate() : null);
        dto.setUsername(member.getAlluser().getUsername());
        return dto;
    }

}
