package br.com.fiap.exception;

import java.time.LocalDateTime;

public class CommitException extends PersistenceException {

    public CommitException() {
    }

    public CommitException(String message) {
        super(message);
    }

    public CommitException(String message, Throwable cause) {
       super(message, cause);
       dateTime = LocalDateTime.now();
    }
}
