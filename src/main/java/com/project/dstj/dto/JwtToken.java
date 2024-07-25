package com.project.dstj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtToken {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private String grantedAuthorities;
    private String placeType;  // 추가된 필드
    private Long userPK;
}
