package com.project.dstj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MemberInfoInquiryDto {
    private UserInfoDto meminfo;
    private Long takesPK;
}