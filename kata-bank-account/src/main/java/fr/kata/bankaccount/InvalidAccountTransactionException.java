package fr.kata.bankaccount;
/**
 * Entity InvalidAccountTransactionException
 *
 * @author bassem
 */
public class InvalidAccountTransactionException extends Exception {
    public InvalidAccountTransactionException(String message) {
        super(message);
    }
}
