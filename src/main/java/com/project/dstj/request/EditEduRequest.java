package com.project.dstj.request;

import lombok.Getter;

@Getter
public class EditEduRequest {
    private Long eduPK;
    private String eduName;
    private String eduDay;
    private String eduStart;
    private String eduEnd;
}
