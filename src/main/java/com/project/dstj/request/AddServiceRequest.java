package com.project.dstj.request;

public class AddServiceRequest {
    private String serviceName;
    private String serviceDay;
    private String serviceStart;
    private String serviceEnd;
    private String workerId; //담당직원 id

    public String getServiceName(){
        return serviceName;
    }

    public String getServiceDay(){
        return serviceDay;
    }
    
    public String getServiceStart(){
        return serviceStart;
    }

    public String getServiceEnd(){
        return serviceEnd;
    }

    public String getWorkerId(){
        return workerId;
    }
}
