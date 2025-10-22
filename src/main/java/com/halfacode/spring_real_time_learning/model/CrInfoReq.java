package com.halfacode.spring_real_time_learning.model;

public class CrInfoReq {
    private String crNumber;

    public CrInfoReq() {}

    public CrInfoReq(String crNumber) {
        this.crNumber = crNumber;
    }

    public String getCrNumber() {
        return crNumber;
    }

    public void setCrNumber(String crNumber) {
        this.crNumber = crNumber;
    }
}
