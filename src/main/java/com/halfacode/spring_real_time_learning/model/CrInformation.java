package com.halfacode.spring_real_time_learning.model;

public class CrInformation {
    private String crNameAR;
    private String crStatus;

    public CrInformation(String crNameAR, String crStatus) {
        this.crNameAR = crNameAR;
        this.crStatus = crStatus;
    }

    public String CRNameAR() {
        return crNameAR;
    }

    public String CRStatus() {
        return crStatus;
    }
}
