package com.project.dstj.dto;

import com.project.dstj.entity.Attendance;
import com.project.dstj.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
//public class MemberListDto {
//    private String userNickName;
//    private LocalDate createDate;
//
//    // Entity -> DTO
//    public static MemberListDto toDto(Member member, Attendance attendance) {
//        MemberListDto dto = new MemberListDto();
//        dto.setUserNickName(member.getAlluser().getUserNickname());
////        dto.setCreateDate(attendance.getCreateDate());
//        dto.setCreateDate(attendance != null ? attendance.getCreateDate() : null);
//        return dto;
//    }
//
//}


public class MemberListDto {
    private String userNickname;
    private LocalDate createDate;

    public MemberListDto(String userNickname, LocalDate createDate) {
        this.userNickname = userNickname;
        this.createDate = createDate;
    }

    public static MemberListDto toDto(Member member, Attendance attendance) {
        LocalDate createDate = (attendance != null) ? attendance.getCreateDate() : null;
        return new MemberListDto(member.getAlluser().getUserNickname(), createDate);
    }
}