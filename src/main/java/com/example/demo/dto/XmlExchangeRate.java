package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlExchangeRate {

    @XmlElement (name = "CharCode")
    private String currencyCode;
    @XmlElement (name = "Name")
    private String currencyName;
    @XmlElement (name = "Nominal")
    private Integer count;
    @XmlElement (name = "Value")
    private String rate;

    public XmlExchangeRate(String currencyCode, String currencyName, Integer count, String rate) {
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.count = count;
        this.rate = rate;
    }
}
