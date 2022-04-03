package br.com.fiap.exception;

import java.time.LocalDateTime;

public class PersistenceException extends Exception{

    protected LocalDateTime dateTime;

    public PersistenceException() {
    }

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
        dateTime = LocalDateTime.now();
    }
}
