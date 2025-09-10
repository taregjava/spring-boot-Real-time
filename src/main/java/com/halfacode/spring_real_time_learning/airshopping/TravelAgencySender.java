package com.halfacode.spring_real_time_learning.airshopping;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
@XmlAccessorType(XmlAccessType.FIELD)
public class TravelAgencySender {
    @XmlElement(name = "Type")
    private String type;

    @XmlElement(name = "IATA_Number")
    private String iataNumber;

    @XmlElement(name = "AgencyID")
    private String agencyId;
}
