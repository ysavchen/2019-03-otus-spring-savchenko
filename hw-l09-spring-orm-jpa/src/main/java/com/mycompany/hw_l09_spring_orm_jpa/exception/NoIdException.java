package com.mycompany.hw_l09_spring_orm_jpa.exception;

/**
 * Thrown to indicate that id is not returned for insert operation.
 */
public class NoIdException extends RuntimeException {

    public NoIdException(String msg) {
        super(msg);
    }
}
