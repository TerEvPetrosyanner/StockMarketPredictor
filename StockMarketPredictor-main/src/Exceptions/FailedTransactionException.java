package Exceptions;

public class FailedTransactionException extends Exception {
    public FailedTransactionException(String errorMessage) {
        super(errorMessage);
    }
}