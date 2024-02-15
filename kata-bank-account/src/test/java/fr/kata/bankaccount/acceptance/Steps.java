package fr.kata.bankaccount.acceptance;

import fr.kata.bankaccount.InvalidAccountTransactionException;
import fr.kata.bankaccount.domain.Account;
import fr.kata.bankaccount.statement.Statement;
import org.hamcrest.Matchers;
import org.jbehave.core.annotations.AsParameterConverter;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertThat;

public class Steps {
    private final Appendable printer = new StringWriter();
    private final Account account = new Account();

    @AsParameterConverter
    public LocalDate createDater(final String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }

    @Given("a client makes a deposit of $amount on $date")
    public void givenAClientMakesADepositOf(final BigDecimal amount, final LocalDate date) throws InvalidAccountTransactionException {
        givenADepositOf(amount, date);
    }

    @Given("a deposit of $amount on $date")
    public void givenADepositOf(final BigDecimal amount, final LocalDate date) throws InvalidAccountTransactionException {
        account.deposit(amount,date);
    }

    @Given("a withdrawal of $amount on $date")
    public void givenAWithdrawalOf(final BigDecimal amount, final LocalDate date) throws InvalidAccountTransactionException {
        account.withdrawal(amount,date);
    }

    @When("she prints her bank statement")
    public void whenSheChecksHerBankStatement() throws IOException {
        final Statement statement = new Statement(account.getTransactionsStream());
        printer.append(statement.toString());
    }

    @Then("she would see $statement")
    public void thenSheWouldSee(final String statement) {
        assertThat(printer.toString(), Matchers.equalTo(statement));
    }
}
