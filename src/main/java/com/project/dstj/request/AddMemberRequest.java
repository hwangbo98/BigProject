package com.project.dstj.request;

public class AddMemberRequest {
    private String username;
    private String password;
    private String userNickname;
    private String userAddress;
    private String userPhoneNumber;
    private String userSignificant;
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
    public String getUserSignificant(){
        return userSignificant;
    }
}
