package com.project.dstj.dto;

import com.project.dstj.entity.Alluser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDto {

    private String username;
    private String password;
    private String userNickname;
    private String userAddress;
    private String userPhoneNumber;
    private String profileImg;
    private String userRole;

    public Alluser toEntity(String encodedPassword, String userRole) {

        return Alluser.builder()
                .username(username)
                .password(encodedPassword)
                .userNickname(userNickname)
                .userAddress(userAddress)
                .userPhoneNumber(userPhoneNumber)
                .profileImg(profileImg)
                .userRole(userRole)
                .build();
    }
}