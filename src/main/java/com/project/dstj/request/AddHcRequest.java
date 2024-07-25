package com.project.dstj.request;

public class AddHcRequest {
    private String hcSex; //성별

    private String hcJob; //직업

    private Float hcHeight; //키

    private Float hcWeight; //몸무게

    private String hcPurpose; //운동목적

    private Float hcTotalbodywater; //체수분

    private Float hcProtein; //단백질

    private Float hcMinerals; //무기질

    private Float hcBodyfatmass; //체지방

    private Float hcSkeletalmusclemass; //골격근량

    private Long memberPK;

    public String getHcSex(){
        return hcSex;
    }

    public String getHcJob(){
        return hcJob;
    }

    public Float getHcHeight(){
        return hcHeight;
    }
    public Float getHcWeight(){
        return hcWeight;
    }
    public String getHcPurpose(){
        return hcPurpose;
    }
    public Float getHcTotalbodywater(){
        return hcTotalbodywater;
    }
    public Float getHcProtein(){
        return hcProtein;
    }
    public Float getHcMinerals(){
        return hcMinerals;
    }
    public Float getHcBodyfatmass(){
        return hcBodyfatmass;
    }
    public Float getHcSkeletalmusclemass(){
        return hcSkeletalmusclemass;
    }
    public Long getMemberPK(){
        return memberPK;
    }


}
