package com.halfacode.spring_real_time_learning.service;

import com.halfacode.spring_real_time_learning.model.CrInfoReq;
import com.halfacode.spring_real_time_learning.model.CrInformation;
import com.halfacode.spring_real_time_learning.model.CrVerificationResponseDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CrService {

    @Cacheable(value = "crInfoCache", key = "#crInfoReq.crNumber", unless = "#result == null")
    public CrVerificationResponseDTO verifyCrInfo(CrInfoReq crInfoReq) throws IOException {
        System.out.println("❌ Calling real method for CR Number: " + crInfoReq.getCrNumber());
        CrInformation crInfo = extractCrInformation(crInfoReq);

        CrVerificationResponseDTO response = new CrVerificationResponseDTO();
        response.setCrNameAr(crInfo.CRNameAR());
        response.setIsActive("Active".equalsIgnoreCase(crInfo.CRStatus()));
        return response;
    }
    private CrInformation extractCrInformation(CrInfoReq req) {
        // Simulate external service call or DB lookup
        return new CrInformation("Test CR Name " + req.getCrNumber(), "Active");
    }

}