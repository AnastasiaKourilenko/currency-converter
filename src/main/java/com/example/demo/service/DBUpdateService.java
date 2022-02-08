package com.example.demo.service;

import com.example.demo.dto.XmlExchangeRate;
import com.example.demo.dto.XmlExchangeRoot;
import com.example.demo.entity.ExchangeRate;
import com.example.demo.repo.ExchangeRateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Slf4j
@Service
public class DBUpdateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    public void getAllRates(LocalDate localDate) {
        XmlExchangeRoot xmlExchangeRoot = restTemplate.getForObject("http://www.cbr.ru/scripts/XML_daily.asp", XmlExchangeRoot.class);
        for (XmlExchangeRate x : xmlExchangeRoot.getXmlExchangeRateList()) {
            ExchangeRate exchangeRate = new ExchangeRate(x.getCurrencyCode(), x.getCurrencyName(), Double.parseDouble(x.getRate().replace(',', '.')) / x.getCount(), localDate);
            exchangeRateRepository.save(exchangeRate);
        }
        ExchangeRate exchangeRateRUB = new ExchangeRate("RUB", "Российский рубль", (double) 1, localDate);
        exchangeRateRepository.save(exchangeRateRUB);
        log.info("База данных заполнена. Получено {} объектов на {}", xmlExchangeRoot.getXmlExchangeRateList().size(), localDate);
    }
}
