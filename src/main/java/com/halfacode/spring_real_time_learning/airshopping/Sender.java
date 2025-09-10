package com.halfacode.spring_real_time_learning.airshopping;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Sender {
    @XmlElement(name = "TravelAgencySender")
    private TravelAgencySender travelAgencySender;
}
