package com.charles.kataquiz.Exception;

public class TriviaApiException extends RuntimeException {
    public TriviaApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public TriviaApiException(String message) {
        super(message);
    }
}
