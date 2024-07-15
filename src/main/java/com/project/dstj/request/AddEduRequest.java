package com.project.dstj.request;

public class AddEduRequest {
    private String eduName;
    private String eduDay;
    private String eduStart;
    private String eduEnd;
    private String workerId; //담당직원 id

    public String getEduName(){
        return eduName;
    }

    public String getEduDay(){
        return eduDay;
    }
    
    public String getEduStart(){
        return eduStart;
    }

    public String getEduEnd(){
        return eduEnd;
    }

    public String getWorkerId(){
        return workerId;
    }
}
