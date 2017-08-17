package com.hari.statistics.controllers;

import com.hari.statistics.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StatisticsController {

    @Autowired
    TransactionService transactionService;


    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public Map statistics(){
        Map<String, Number> statistics =  transactionService.getStatistics();
        return statistics;
    }
}

