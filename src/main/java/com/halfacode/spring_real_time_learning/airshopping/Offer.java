package com.halfacode.spring_real_time_learning.airshopping;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Offer {
    @XmlAttribute(name = "OfferID")
    private String offerId;

    @XmlAttribute(name = "Owner")
    private String owner;

    @XmlAttribute(name = "ResponseID")
    private String responseId;

    @XmlElement(name = "OfferItem")
    private List<OfferItem> offerItems;
}
