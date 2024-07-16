package com.project.dstj.request;

import lombok.Getter;

@Getter
public class EditWorkerRequest {
    private Long workerPK;
    private String userNickname;
    private int workerSalary;

}
