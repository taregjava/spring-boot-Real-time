package com.halfacode.spring_real_time_learning.airshopping;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "OfferPriceRQ", namespace = "http://www.iata.org/IATA/EDIST/2017.2")
@XmlType(propOrder = {"document", "party", "query", "preference", "dataLists"})
@Data
public class OfferPriceRQ {

    @XmlAttribute(name = "Version")
    private String version = "17.2";

    @XmlElement(name = "Document")
    private Document document;

    @XmlElement(name = "Party")
    private Party party;

    @XmlElement(name = "Query")
    private Query query;

    @XmlElement(name = "Preference")
    private Preference preference;

    @XmlElement(name = "DataLists")
    private DataLists dataLists;

}