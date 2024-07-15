package com.project.dstj.request;

public class AddTakesRequest {
    private Long eduPK;
    private String memberId;

    public Long getServicePK(){
        return eduPK;
    }
    
    public String getMemberId(){
        return memberId;
    }
}
