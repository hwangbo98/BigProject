package com.project.dstj.dto;

import com.project.dstj.entity.Alluser;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlluserDto {

    private Long userPK;
    private String username; // 로그인id
    private String userNickname; // 이름
    private String userAddress; // 주소
    private String userPhoneNumber; //전화번호
    private String profileImg;

    static public AlluserDto toDto(Alluser alluser) {
        return AlluserDto.builder()
                .userPK(alluser.getUserPK())
                .username(alluser.getUsername())
                .userNickname(alluser.getUserNickname())
                .userAddress(alluser.getUserAddress())
                .userPhoneNumber(alluser.getUserPhoneNumber())
                .profileImg(alluser.getProfileImg()).build();
    }

    public Alluser toEntity() {
        return Alluser.builder()
                .userPK(userPK)
                .username(username)
                .userNickname(userNickname)
                .userAddress(userAddress)
                .userPhoneNumber(userPhoneNumber)
                .profileImg(profileImg).build();
    }
}
