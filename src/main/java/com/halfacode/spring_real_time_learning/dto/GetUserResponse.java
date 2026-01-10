package com.halfacode.spring_real_time_learning.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "name",
        "email"
})
@XmlRootElement(name = "GetUserResponse", namespace = "http://example.com/soap/user")
public class GetUserResponse {

    @XmlElement(required = true)
    protected String name;

    @XmlElement(required = true)
    protected String email;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }
}
