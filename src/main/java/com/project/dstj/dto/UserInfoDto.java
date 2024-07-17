package com.project.dstj.dto;

import java.time.LocalDate;

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
    private String hcSex;
    private Float hcHeight; //키
    private Float hcWeight; //몸무게
    private LocalDate hcDate; //측정날짜
    private String hcPurpose; //운동목적
    private Float hcTotalbodywater; //체수분
    private Float hcProtein; //단백질
    private Float hcMinerals; //무기질
    private Float hcBodyfatmass; //체지방
    private Float hcSkeletalmusclemass; //골격근량
    private String hcReport; //ai리포트 결과 조언
    private Long acPK;
    private String acGrade;
    private String acSchool;
    private String acClass;
    private String acParent;
    private LocalDate acDate;
    // private String acSignificant;


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
                .hcSex(hc != null ? hc.getHcSex() : null)
                .hcHeight(hc != null ? hc.getHcHeight() : null)
                .hcWeight(hc != null ? hc.getHcWeight() : null)
                .hcDate(hc != null ? hc.getHcDate() : null)
                .hcPurpose(hc != null ? hc.getHcPurpose() : null)
                .hcTotalbodywater(hc != null ? hc.getHcTotalbodywater() : null)
                .hcProtein(hc != null ? hc.getHcProtein() : null)
                .hcMinerals(hc != null ? hc.getHcMinerals() : null)
                .hcBodyfatmass(hc != null ? hc.getHcBodyfatmass() : null)
                .hcSkeletalmusclemass(hc != null ? hc.getHcSkeletalmusclemass() : null)
                .hcReport(hc != null ? hc.getHcReport() : null)
                .acPK(ac != null ? ac.getAcPK() : null)
                .acGrade(ac != null ? ac.getAcGrade() : null)
                .acSchool(ac != null ? ac.getAcSchool() : null)
                .acClass(ac != null ? ac.getAcClass() : null)
                .acParent(ac != null ? ac.getAcParent() : null)
                .acDate(ac != null ? ac.getAcDate() : null)
                .build();
    }
}
