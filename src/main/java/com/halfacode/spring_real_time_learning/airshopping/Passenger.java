package com.halfacode.spring_real_time_learning.airshopping;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Passenger {

    @XmlAttribute(name = "PassengerID")
    private String passengerId;

    @XmlElement(name = "PTC")
    private String ptc;

    @XmlElement(name = "Birthdate")
    private String birthdate;

    @XmlElement(name = "ResidenceCountryCode")
    private String residenceCountryCode;

    @XmlElement(name = "CitizenshipCountryCode")
    private String citizenshipCountryCode;
}
