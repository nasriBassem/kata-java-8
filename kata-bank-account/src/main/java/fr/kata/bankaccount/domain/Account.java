package fr.kata.bankaccount.domain;

import fr.kata.bankaccount.InvalidAccountTransactionException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Entity Class Account
 *
 * @author bassem
 */
public class Account {
    private List<AccountTransaction> transactions = new ArrayList<>();

    public void deposit(final BigDecimal amount, final LocalDate date) throws InvalidAccountTransactionException {
        createTransaction(new Deposit(amount, date));
    }
    public void withdrawal(final BigDecimal amount, final LocalDate date) throws InvalidAccountTransactionException {
        if(amount.compareTo(getBalance()) > 0)
            throw new InvalidAccountTransactionException("Account not sufficiently provisioned : " +  getBalance() + " < " + amount);
         createTransaction(new Withdrawal(amount, date));
    }
    private void createTransaction(final AccountTransaction accountTransaction) throws InvalidAccountTransactionException {
        if(accountTransaction.getAmount() == null || accountTransaction.getAmount().compareTo(new BigDecimal(0)) == 0
                || accountTransaction.getAmount().compareTo(new BigDecimal(0)) < 0)
            throw new InvalidAccountTransactionException("Incorrect amount for a transaction : amount should be >0 ");
        transactions.add(accountTransaction);
    }
    protected List<AccountTransaction> getHistory() {
        transactions.sort(Comparator.comparing(AccountTransaction::getDate));
        return transactions;
    }
    protected BigDecimal getBalance() {
        BigDecimal balance = new BigDecimal(0);
        for (AccountTransaction transaction : transactions) {
            balance = transaction.adjust(balance);
        }
        return balance;
    }
    public Stream<AccountTransaction> getTransactionsStream() {
        return transactions.stream();
    }
}
