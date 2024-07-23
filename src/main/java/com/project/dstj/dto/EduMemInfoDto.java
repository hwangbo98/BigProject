package com.project.dstj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EduMemInfoDto {
    private Long userPK;
    private String username;
    private String userNickname;
    private Long takesPK;
}
