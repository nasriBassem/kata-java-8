package fr.kata.bankaccount.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * Entity Class AccountTransaction Deposit
 *
 * @author bassem
 */
public class Deposit extends AccountTransaction{
    public Deposit(BigDecimal amount, LocalDate date) {
        super(amount, date);
    }
    @Override
    public BigDecimal adjust(BigDecimal balance) {
        return balance.add(this.getAmount());
    }
}
