package com.example.demo.controller;

import com.example.demo.service.CurrencyConverterService;
import com.example.demo.service.OperationHistoryService;
import com.example.demo.dto.XmlExchangeRate;
import com.example.demo.dto.XmlExchangeRoot;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.time.LocalDate;

@RestController
public class CurrencyConverterController {

    @Autowired
    private CurrencyConverterService currencyConverterService;
    @Autowired
    private OperationHistoryService operationHistoryService;


    @GetMapping("/convert")
    public String convert(@RequestParam(value = "from") String sourceCurrency, @RequestParam(value = "to") String targetCurrency, @RequestParam(value = "amount") Double amount) {
        LocalDate localDate = LocalDate.now();
        Double result = currencyConverterService.convert(sourceCurrency, targetCurrency, amount, localDate);
        operationHistoryService.saveOperation(sourceCurrency, targetCurrency, amount, result, localDate);
        return sourceCurrency + " " + amount.toString() + " -> " + targetCurrency + " " + result.toString();
    }

    @GetMapping("/xmlHelp")
    @SneakyThrows
    public String xmlHelp() {
        XmlExchangeRoot xmlExchangeRoot = new XmlExchangeRoot();
        XmlExchangeRate xmlExchangeRate1 = new XmlExchangeRate("USD", "Американский доллар", 1, "73.42");
        XmlExchangeRate xmlExchangeRate2 = new XmlExchangeRate("EUR", "Евро", 1, "85.04");
        xmlExchangeRoot.getXmlExchangeRateList().add(xmlExchangeRate1);
        xmlExchangeRoot.getXmlExchangeRateList().add(xmlExchangeRate2);
        JAXBContext context = JAXBContext.newInstance(XmlExchangeRoot.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        Result result = new StreamResult(sw);
        mar.marshal(xmlExchangeRoot, result);
        return sw.toString();
    }

}
