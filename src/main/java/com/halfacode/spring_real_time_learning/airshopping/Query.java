package com.halfacode.spring_real_time_learning.airshopping;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Query {

    @XmlElement(name = "Offer")
    private Offer offer;
}
