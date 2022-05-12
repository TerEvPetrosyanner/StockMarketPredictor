package Exceptions;

public class FailedTransactionException extends Exception {
    public FailedTransactionException(){super("Failed Transaction"); }
    public FailedTransactionException(String errorMessage) {
        super(errorMessage);
    }
}