package com.project.dstj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EduDto {
    private Long eduPK;
    private String eduDay;
    private String eduEnd;
    private String eduName;
    private String eduStart;
    private Long placePK;
    private Long workerPK;
    private String userNickname;
    private String username;
}
