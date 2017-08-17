package com.hari.statistics.services;

import com.hari.statistics.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Predicate;

@Service
public class TransactionService {

    List<Transaction> transactionsList = new ArrayList();
    private static final Long ONE_MINUTE_IN_MILLIS = 60 * 1000l;

    public Transaction add(Transaction transaction){
        transactionsList.add(transaction);
        return transaction;
    }

    public Map<String, Number> getStatistics(){
        Map statistics = new HashMap<String, Number>();
        Double sum = transactionsList.stream().mapToDouble(transaction -> transaction.getAmount()).sum();
        OptionalDouble average = transactionsList.stream().mapToDouble(transaction -> transaction.getAmount()).average();
        OptionalDouble max = transactionsList.stream().mapToDouble(transaction -> transaction.getAmount()).max();
        OptionalDouble min = transactionsList.stream().mapToDouble(transaction -> transaction.getAmount()).min();
        Integer count = transactionsList.size();
        statistics.put("sum", sum);
        statistics.put("average", average.isPresent() ? average.getAsDouble() : 0.0);
        statistics.put("max", max.isPresent() ? max.getAsDouble(): 0.0);
        statistics.put("min", min.isPresent() ? min.getAsDouble() : 0.0);
        statistics.put("count", count);
        return  statistics;
    }

    public void deleteOldTransactions(){
        removeMatching(transactionsList.iterator(), isOlderThanOneMinute());
    }

    public static <E> void removeMatching(final Iterator<E> it, final Predicate<E> predicate) {
        while (it.hasNext()) {
            final E e = it.next();
            if (predicate.test(e)) {
                it.remove();
            }
        }
    }

    private static Predicate<Transaction> isOlderThanOneMinute() {
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        long epochInMillis = utc.toEpochSecond() * 1000;
        return transaction ->
                transaction.getTimestamp() < epochInMillis - ONE_MINUTE_IN_MILLIS;
    }


}
