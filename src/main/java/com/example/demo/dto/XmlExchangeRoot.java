package com.example.demo.dto;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlExchangeRoot {

    @XmlElement(name = "Valute")
    private List<XmlExchangeRate> xmlExchangeRateList = new ArrayList<>();


}
