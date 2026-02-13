/*
 * Author: Charles Loeffler
 * Last Updated: 02/12/2026
 */

package com.charles.kataquiz.Exception;

/**
 * Exception thrown when an error occurs while
 * communicating with the Open Trivia Database API.
 */
public class TriviaApiException extends RuntimeException {

    /**
     * Creates a new TriviaApiException with a message and cause.
     *
     * @param message the error message
     * @param cause the underlying cause of the exception
     */
    public TriviaApiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new TriviaApiException with a message.
     *
     * @param message the error message
     */
    public TriviaApiException(String message) {
        super(message);
    }
}
