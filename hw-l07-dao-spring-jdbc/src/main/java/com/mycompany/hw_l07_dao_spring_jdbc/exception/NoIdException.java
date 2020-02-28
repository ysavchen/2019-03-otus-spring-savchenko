package com.mycompany.hw_l07_dao_spring_jdbc.exception;

/**
 * Thrown to indicate that id is not returned for insert operation.
 */
public class NoIdException extends RuntimeException {

    public NoIdException(String msg) {
        super(msg);
    }
}
