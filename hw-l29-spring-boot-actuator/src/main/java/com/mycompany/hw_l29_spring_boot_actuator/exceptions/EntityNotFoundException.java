package com.mycompany.hw_l29_spring_boot_actuator.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

}
