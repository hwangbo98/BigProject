package com.project.dstj.request;

public class AddWorkerRequest {
    private String username;
    private String password;
    private String userNickname;
    private String userAddress;
    private String userPhoneNumber;
    
    private int workerSalary;

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    public String getUserNickname(){
        return userNickname;
    }
    public String getUserAddress(){
        return userAddress;
    }
    public String getUserPhoneNumber(){
        return userPhoneNumber;
    }
    public int getWorkerSalary(){
        return workerSalary;
    }
}
