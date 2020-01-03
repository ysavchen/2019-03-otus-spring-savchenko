package com.mycompany.hw_l01_spring_introduction.exceptions;

/**
 * Thrown to indicate a mismatch between question and answer id
 */
public class QuestionMismatchException extends RuntimeException {

    public QuestionMismatchException(String s) {
        super(s);
    }
}
