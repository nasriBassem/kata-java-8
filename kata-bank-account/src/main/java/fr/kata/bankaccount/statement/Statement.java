package fr.kata.bankaccount.statement;

import fr.kata.bankaccount.domain.AccountTransaction;
import java.util.stream.Stream;

/**
 * Entity Class Statement to print account transaction details
 *
 * @author bassem
 */
public class Statement {
    private Stream<AccountTransaction> operations;
    public Statement(Stream<AccountTransaction> operations) {
        this.operations = operations;
    }
    public String toString() {
        return operations.reduce(new StatementLine(), StatementLine::add, StatementLine::merge).toString();
    }
}

