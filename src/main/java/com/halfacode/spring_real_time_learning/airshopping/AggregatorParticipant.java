package com.halfacode.spring_real_time_learning.airshopping;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class AggregatorParticipant {
    @XmlAttribute(name = "SequenceNumber")
    private int sequenceNumber;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "AggregatorID")
    private String aggregatorId;
}
