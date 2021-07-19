package com.iteratrlearning.shu_book.chapter_02;

//import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class BankStatementProcessorTest {

    private BankStatementProcessor bankStatementProcessor;

    @Before
    public void setUp() {
        List<BankTransaction> bankTransactions = List.of(
                new BankTransaction(LocalDate.of(2019, Month.DECEMBER, 31), -20_000, "Invalid"),
                new BankTransaction(LocalDate.of(2020, Month.JANUARY, 1), 1_000.2215, "First year's day"),
                new BankTransaction(LocalDate.of(2020, Month.JANUARY, 01), 1_000, "First year's zeroed day"),
                new BankTransaction(LocalDate.of(2020, Month.FEBRUARY, 14), 500, "San Valentin"),
                new BankTransaction(LocalDate.of(2020, Month.JULY, 23), 2_000, "Holidays"),
                new BankTransaction(LocalDate.of(2020, Month.NOVEMBER, 05), 2_000.0001, "Holidays"),
                new BankTransaction(LocalDate.of(2020, Month.JUNE, 30), 2_000.0002, "Holidays"),
                new BankTransaction(LocalDate.of(2021, Month.JANUARY, 01), -10_000, "Invalid"),
                new BankTransaction(LocalDate.of(2021, Month.JANUARY, 1), 20_000, "Invalid")
        );
        bankStatementProcessor = new BankStatementProcessor(bankTransactions);
    }

    @Test
    public void shouldFindMaxAmountInRange() {
        // Given
        LocalDate startDate = LocalDate.of(2020, Month.JANUARY, 1);
        LocalDate endDate = LocalDate.of(2020, Month.DECEMBER, 31);

        // When
        double maxTransaction = bankStatementProcessor.findMaximumMovementInRange(startDate, endDate);

        // Then
        Assert.assertEquals(2_000.0002, maxTransaction, 0.0);
    }

}