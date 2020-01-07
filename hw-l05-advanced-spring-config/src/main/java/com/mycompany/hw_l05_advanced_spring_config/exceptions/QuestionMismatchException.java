package com.mycompany.hw_l05_advanced_spring_config.exceptions;

/**
 * Thrown to indicate a mismatch between question and answer id
 */
public class QuestionMismatchException extends RuntimeException {

    public QuestionMismatchException(String s) {
        super(s);
    }
}
