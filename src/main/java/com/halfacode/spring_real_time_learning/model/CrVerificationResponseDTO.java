package com.halfacode.spring_real_time_learning.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class CrVerificationResponseDTO {
    private String crNameAr;
    private boolean active;


    public String getCrNameAr() {
        return crNameAr;
    }

    public void setCrNameAr(String crNameAr) {
        this.crNameAr = crNameAr;
    }

    public boolean isActive() {
        return active;
    }

    public void setIsActive(boolean active) {
        active = active;
    }

    @Override
    public String toString() {
        return "CrVerificationResponseDTO{" +
                "crNameAr='" + crNameAr + '\'' +
                ", active=" + active +
                '}';
    }
}
