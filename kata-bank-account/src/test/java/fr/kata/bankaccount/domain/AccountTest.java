package fr.kata.bankaccount.domain;

import fr.kata.bankaccount.InvalidAccountTransactionException;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class AccountTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    private Account accountOne;

    @Before
    public void prepare() {
        accountOne = new Account();
    }
    @Test
    public void should_exception_amount_zero() throws InvalidAccountTransactionException {
        expectedEx.expect(InvalidAccountTransactionException.class);
        expectedEx.expectMessage("Incorrect amount for a transaction : amount should be >0 ");
        accountOne.deposit(new BigDecimal(0), LocalDate.of(2024, 1, 12));
    }
    @Test
    public void should_exception_amount_null() throws InvalidAccountTransactionException {
        expectedEx.expect(InvalidAccountTransactionException.class);
        expectedEx.expectMessage("Incorrect amount for a transaction : amount should be >0 ");
        accountOne.deposit(null, LocalDate.of(2024, 1, 12));
    }
    @Test
    public void should_exception_amount_negative_value() throws InvalidAccountTransactionException {
        expectedEx.expect(InvalidAccountTransactionException.class);
        expectedEx.expectMessage("Incorrect amount for a transaction : amount should be >0 ");
        accountOne.deposit(new BigDecimal(-10), LocalDate.of(2024, 1, 12));
    }
    @Test
    public void should_exception_not_sufficiently_amount() throws  InvalidAccountTransactionException{
        expectedEx.expect(InvalidAccountTransactionException.class);
        expectedEx.expectMessage("Account not sufficiently provisioned : " +  new BigDecimal(220)+ " < " + new BigDecimal(230));
        accountOne.deposit(new BigDecimal(220), LocalDate.of(2024, 1, 12));
        accountOne.withdrawal(new BigDecimal(230), LocalDate.of(2024, 1, 12));
    }
    @Test
    public void should_add_transaction_ok_get_balance() throws  InvalidAccountTransactionException{
        accountOne.deposit(new BigDecimal(10), LocalDate.of(2024, 1, 12));
        accountOne.deposit(new BigDecimal(10), LocalDate.of(2024, 1, 13));
        accountOne.deposit(new BigDecimal(10), LocalDate.of(2024, 1, 14));
        accountOne.withdrawal(new BigDecimal(10), LocalDate.of(2024, 1, 15));
        assertEquals(new BigDecimal(20), accountOne.getBalance());
    }
    @Test
    public void should_add_transaction_ok_get_history_sorted_date() throws InvalidAccountTransactionException {
        accountOne.deposit(new BigDecimal(10), LocalDate.of(2024, 1, 13));
        accountOne.deposit(new BigDecimal(10), LocalDate.of(2024, 1, 12));
        accountOne.withdrawal(new BigDecimal(10), LocalDate.of(2024, 1, 15));
        accountOne.deposit(new BigDecimal(10), LocalDate.of(2024, 1, 14));
        assertEquals(4, accountOne.getHistory().size());
        assertEquals(new Deposit(new BigDecimal(10), LocalDate.of(2024, 1, 12)), accountOne.getHistory().get(0));
        assertEquals(new Deposit(new BigDecimal(10), LocalDate.of(2024, 1, 13)), accountOne.getHistory().get(1));
        assertEquals(new Withdrawal(new BigDecimal(10), LocalDate.of(2024, 1, 14)), accountOne.getHistory().get(2));
        assertEquals(new Deposit(new BigDecimal(10), LocalDate.of(2024, 1, 15)), accountOne.getHistory().get(3));
    }

}
