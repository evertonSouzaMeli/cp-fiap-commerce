package br.com.fiap.exception;

public class IdNotFoundException extends PersistenceException {

    public IdNotFoundException() {
    }

    public IdNotFoundException(String message) {
        super(message);
    }

    public IdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
