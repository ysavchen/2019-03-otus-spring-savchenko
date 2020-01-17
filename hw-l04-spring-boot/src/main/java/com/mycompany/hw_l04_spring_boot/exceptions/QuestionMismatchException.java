package com.mycompany.hw_l04_spring_boot.exceptions;

/**
 * Thrown to indicate a mismatch between question and answer id
 */
public class QuestionMismatchException extends RuntimeException {

    public QuestionMismatchException(String s) {
        super(s);
    }
}
