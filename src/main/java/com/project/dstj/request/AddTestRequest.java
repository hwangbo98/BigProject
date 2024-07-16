package com.project.dstj.request;

public class AddTestRequest {
    private Long takesPK;
    private String testResult;
    private String testName;

    public Long getTakesPK(){
        return takesPK;
    }
    public String getTestResult(){
        return testResult;
    }
    public String getTestName(){
        return testName;
    }
}
