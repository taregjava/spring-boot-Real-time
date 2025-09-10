package com.halfacode.spring_real_time_learning.airshopping;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;
@XmlAccessorType(XmlAccessType.FIELD)
@Data
class Document {
    @XmlElement(name = "Name")
    private String name;
}