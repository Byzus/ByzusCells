package dev.byzus.byzuscells.exception;

public class CellAlreadyExistsException extends RuntimeException {

    public CellAlreadyExistsException() {
        super();
    }

    public CellAlreadyExistsException(String message) {
        super(message);
    }

    public CellAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CellAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    protected CellAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
