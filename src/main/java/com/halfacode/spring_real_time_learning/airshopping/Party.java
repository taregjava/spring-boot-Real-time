package com.halfacode.spring_real_time_learning.airshopping;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
@XmlAccessorType(XmlAccessType.FIELD)
@Data
class Party {
    @XmlElement(name = "Sender")
    private Sender sender;

    @XmlElement(name = "Participants")
    private Participants participants;
}