package fr.kata.bankaccount.statement;

import fr.kata.bankaccount.InvalidAccountTransactionException;
import fr.kata.bankaccount.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class StatementTest {
    @Test
    public void should_print_statement_header(){
        final Statement statement = new Statement(Stream.empty());
        assertEquals("operation  |       date |  amount | balance", statement.toString());
    }
    @Test
    public void should_print_deposit() throws InvalidAccountTransactionException {
        final Account account = new Account();
        account.deposit(new BigDecimal(10), LocalDate.of(2024, 1, 12));
        account.withdrawal(new BigDecimal(10), LocalDate.of(2024, 1, 12));
        final Statement statement = new Statement(account.getTransactionsStream());
        assertEquals(
                "operation  |       date |  amount | balance\n"
                        + "Withdrawal | 12/01/2024 |      10 | 0\n"
                        + "Deposit    | 12/01/2024 |      10 | 10",
                statement.toString());
    }
}
