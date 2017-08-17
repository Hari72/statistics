package com.hari.statistics.scheduler;

import com.hari.statistics.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class DataCleaner {

    private static final long ONE_MINUTE_IN_MILLIS = 60 * 1000l;

    @Autowired
    TransactionService transactionService;

    @Scheduled(fixedRate = 1000)
    public void cleanOldData() {
        transactionService.deleteOldTransactions();
    }
}