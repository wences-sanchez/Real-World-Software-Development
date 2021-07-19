package com.iteratrlearning.shu_book.chapter_02;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        double total = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public double calculateTotalInMonth(final Month month) {
        double total = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {

                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public double calculateTotalForCategory(final String category) {
        double total = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDescription().equals(category)) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    /**
     * Find the maximum transaction amount in specific date ranges
     *
     * @return the maximum transaction amount between the range specified
     */
    public double findMaximumMovementInRange(final LocalDate startDate, final LocalDate endDate) {
        double max = 0;
        for (BankTransaction transaction : bankTransactions) {
            if (transaction.getDate().isAfter(startDate) && transaction.getDate().isBefore(endDate)
                    && transaction.getAmount() >= max) {
                max = transaction.getAmount();
            }
        }
        return max;
    }
}
