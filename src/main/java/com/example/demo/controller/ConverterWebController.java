package com.example.demo.controller;

import com.example.demo.service.CurrencyConverterService;
import com.example.demo.entity.ExchangeOperation;
import com.example.demo.entity.ExchangeRate;
import com.example.demo.service.OperationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ConverterWebController {

    @Autowired
    CurrencyConverterService currencyConverterService;
    @Autowired
    OperationHistoryService operationHistoryService;

    @GetMapping("/converter")
    public String converter(@RequestParam(name = "from", required = false) String sourceCurrency,
                            @RequestParam(name = "to", required = false) String targetCurrency,
                            @RequestParam(name = "amount", required = false) Double sourceAmount,
                            Model model) {
        LocalDate localDate = LocalDate.now();
        if (sourceCurrency != null && targetCurrency != null && sourceAmount != null) {
            Double result = currencyConverterService.convert(sourceCurrency, targetCurrency, sourceAmount, localDate);
            model.addAttribute("sourceCurrency", sourceCurrency);
            model.addAttribute("targetCurrency", targetCurrency);
            model.addAttribute("sourceAmount", sourceAmount);
            model.addAttribute("result", result);
            operationHistoryService.saveOperation(sourceCurrency, targetCurrency, sourceAmount, result, localDate);
        }
        List<ExchangeRate> exchangeRates = currencyConverterService.getAllExchangeRates();
        model.addAttribute("exchangeRates", exchangeRates);
        return "converter";
    }

    @GetMapping("/history")
    public String history(@RequestParam(name = "date", required = false) String date,
                          Model model) {
        if(date!=null) {
            List<ExchangeOperation> exchangeOperations = operationHistoryService.getAllExchangeOperations(LocalDate.parse(date));
            model.addAttribute("exchangeOperations", exchangeOperations);
        }

        return "history";
    }
}