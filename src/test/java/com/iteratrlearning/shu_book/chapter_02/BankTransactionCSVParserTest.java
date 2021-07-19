package com.iteratrlearning.shu_book.chapter_02;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class BankTransactionCSVParserTest {

    private BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception {
        String line = "30-01-2017,-50,Tesco";

        BankTransaction result = statementParser.parseFrom(line);

        BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");
        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), 0.0d);
        Assert.assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void shouldParseLongerLineWithExtraData() {
        // Given
        String lineWithExtraData = "07-07-2021,100,Gift,Extra field";

        // When
        BankTransaction result = statementParser.parseFrom(lineWithExtraData);

        // Then
        BankTransaction expected = new BankTransaction(
                LocalDate.of(2021, Month.JULY, 7), 100, "Gift");
        assertEquals(expected.getDate(), result.getDate());
        assertEquals(expected.getAmount(), result.getAmount(), 0.0d);
        assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void shouldHandleInvalidData() {
        // Given
        String invalidDataLine = "1-01-2000,-10.5,Fuel"; // Date format is: day-month-year

        // When
        BankTransaction result = statementParser.parseFrom(invalidDataLine);

        // Then
        BankTransaction expected = new BankTransaction(
                LocalDate.of(2000, Month.JANUARY, 1), -10.5, "Fuel");
        assertEquals(expected.getDate(), result.getDate());
        assertEquals(expected.getAmount(), result.getAmount(), 0.0d);
        assertEquals(expected.getDescription(), result.getDescription());
    }

}
