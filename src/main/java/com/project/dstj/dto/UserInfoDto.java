package com.project.dstj.dto;

import com.project.dstj.entity.Ac;
import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Hc;
import com.project.dstj.entity.Test;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDto {
    private Long userPK;
    private String username; // 로그인 id
    private String userNickname; // 이름
    private String userAddress; // 주소
    private String userPhoneNumber; // 전화번호
    private String profileImg;
    private String userRole; // 권한
    private Long hcPK;
    private Float hcHeight;
    private Float hcWeight;
    private String hcDate;
    private Long acPK;
    private String acGrade;
    private String acSchool;
    private String acClass;
    private String acParent;
    private String acDate;
    private String acSignificant;


    public static UserInfoDto toDto(Alluser alluser, Ac ac, Hc hc, Test test) {
        return UserInfoDto.builder()
                .userPK(alluser.getUserPK())
                .username(alluser.getUsername())
                .userNickname(alluser.getUserNickname())
                .userAddress(alluser.getUserAddress())
                .userPhoneNumber(alluser.getUserPhoneNumber())
                .profileImg(alluser.getProfileImg())
                .userRole(alluser.getUserRole())
                .hcPK(hc != null ? hc.getHcPK() : null)
                .hcHeight(hc != null ? hc.getHcHeight() : null)
                .hcWeight(hc != null ? hc.getHcWeight() : null)
                .hcDate(hc != null ? hc.getHcDate().toString() : null)
                .acPK(ac != null ? ac.getAcPK() : null)
                .acGrade(ac != null ? ac.getAcGrade() : null)
                .acSchool(ac != null ? ac.getAcSchool() : null)
                .acClass(ac != null ? ac.getAcClass() : null)
                .acParent(ac != null ? ac.getAcParent() : null)
                .acDate(ac != null ? ac.getAcDate().toString() : null)
                .acSignificant(ac != null ? ac.getAcSignificant() : null)
                .build();
    }
}