package fr.kata.bankaccount.domain;
import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * Entity Class AccountTransaction Withdrawal
 *
 * @author bassem
 */
public class Withdrawal extends AccountTransaction {
    public Withdrawal( final BigDecimal amount, final LocalDate date) {
        super( amount, date);
    }
    public BigDecimal adjust(final BigDecimal balance) {
        return balance.subtract(this.getAmount());
    }
}
