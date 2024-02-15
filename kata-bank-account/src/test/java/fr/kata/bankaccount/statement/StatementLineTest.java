package fr.kata.bankaccount.statement;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StatementLineTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void should_exception_amount_zero(){
        expectedEx.expect(UnsupportedOperationException.class);
        new StatementLine().merge(new StatementLine());
    }
}
