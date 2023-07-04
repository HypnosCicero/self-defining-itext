package org.oceanT.tools;


import java.util.Date;

public class User {
    private String aName;
    private String bName;
    private Integer aIDNo;
    private Integer bIDNo;
    private String fuzeren;
    private double a;
    private boolean bGnaration;
    private String bGender;
    private Date startDate;

    public User() {
    }

    public User(String aName, String bName, Integer aIDNo, Integer bIDNo, String fuzeren, double a, boolean bGnaration, String bGender, Date startDate) {
        this.aName = aName;
        this.bName = bName;
        this.aIDNo = aIDNo;
        this.bIDNo = bIDNo;
        this.fuzeren = fuzeren;
        this.a = a;
        this.bGnaration = bGnaration;
        this.bGender = bGender;
        this.startDate = startDate;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public Integer getaIDNo() {
        return aIDNo;
    }

    public void setaIDNo(Integer aIDNo) {
        this.aIDNo = aIDNo;
    }

    public Integer getbIDNo() {
        return bIDNo;
    }

    public void setbIDNo(Integer bIDNo) {
        this.bIDNo = bIDNo;
    }

    public String getFuzeren() {
        return fuzeren;
    }

    public void setFuzeren(String fuzeren) {
        this.fuzeren = fuzeren;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public boolean isbGnaration() {
        return bGnaration;
    }

    public void setbGnaration(boolean bGnaration) {
        this.bGnaration = bGnaration;
    }

    public String getbGender() {
        return bGender;
    }

    public void setbGender(String bGender) {
        this.bGender = bGender;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
