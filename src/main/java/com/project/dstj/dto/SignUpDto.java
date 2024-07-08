package com.project.dstj.dto;

import com.project.dstj.entity.Member;
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
    private String nickname;
    private String address;
    private String phone;
    private String profileImg;
    private String role;

    public Member toEntity(String encodedPassword, String role) {

        return Member.builder()
                .username(username)
                .password(encodedPassword)
                .nickname(nickname)
                .address(address)
                .phone(phone)
                .profileImg(profileImg)
                .role(role)
                .build();
    }
}