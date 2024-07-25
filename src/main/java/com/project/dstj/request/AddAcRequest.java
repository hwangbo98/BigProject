package com.project.dstj.request;

public class AddAcRequest {
    private Long memberPK; //회원pk

    private String acGrade; //학년

    private String acSchool; //학교

    private String acClass; //소속반

    private String acParent; //학부모번호

    public Long getMemberPK(){
        return memberPK;
    }
    public String getAcGrade(){
        return acGrade;
    }
    public String getAcSchool(){
        return acSchool;
    }
    public String getAcClass(){
        return acClass;
    }
    public String getAcParent(){
        return acParent;
    }
}