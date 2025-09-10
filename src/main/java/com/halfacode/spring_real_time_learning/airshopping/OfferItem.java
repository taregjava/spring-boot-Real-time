package com.halfacode.spring_real_time_learning.airshopping;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class OfferItem {

    @XmlAttribute(name = "OfferItemID")
    private String offerItemId;

    @XmlElement(name = "PassengerRefs")
    private String passengerRefs;
}
