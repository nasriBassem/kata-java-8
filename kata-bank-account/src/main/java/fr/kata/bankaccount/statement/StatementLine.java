package fr.kata.bankaccount.statement;

import fr.kata.bankaccount.domain.AccountTransaction;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Entity Class StatementLine to print account transaction details
 *
 * @author bassem
 */
@NoArgsConstructor
public class StatementLine {
    private final Deque<String> lines = new LinkedList<>();
    private BigDecimal balance = new BigDecimal(0);

    StatementLine add(final AccountTransaction operation) {
        balance = operation.adjust(balance);
        lines.addFirst(String.format("%1$-10s | %2$td/%2$tm/%2$tY | %3$7s | %4$s",
                operation.getClass().getSimpleName(),
                operation.getDate(),
                operation.getAmount(),
                balance));
        return this;
    }
    public StatementLine merge(final StatementLine other) {
        throw new UnsupportedOperationException();
    }
    @Override
    public String toString() {
        lines.addFirst("operation  |       date |  amount | balance");
        return String.join("\n", lines);
    }
}
