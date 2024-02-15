package fr.kata.bankaccount.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Entity Class AccountTransaction :
 *
 * @author bassem
 */
public abstract class AccountTransaction {
    private BigDecimal amount;
    private LocalDate date;

    protected AccountTransaction( final BigDecimal amount, final LocalDate date) {
        this.amount = amount;
        this.date = date;
    }
    public abstract BigDecimal adjust( final BigDecimal balance);
    public BigDecimal getAmount() {
        return this.amount;
    }
    public LocalDate getDate() {
        return this.date;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountTransaction)) return false;
        AccountTransaction that = (AccountTransaction) o;
        return Objects.equals(getAmount(), that.getAmount()) && Objects.equals(getDate(), that.getDate());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getAmount(), getDate());
    }
}
