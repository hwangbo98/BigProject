package com.project.dstj.request;

public class AddEduRequest {
    private String eduName;
    private String eduDay;
    private String eduStart;
    private String eduEnd;
    private String workerId; //담당직원 id

    public String getServiceName(){
        return eduName;
    }

    public String getServiceDay(){
        return eduDay;
    }
    
    public String getServiceStart(){
        return eduStart;
    }

    public String getServiceEnd(){
        return eduEnd;
    }

    public String getWorkerId(){
        return workerId;
    }
}
