package com.springdemo.ipvproject.exception;


public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("Email déjà utilisé : " + email);
    }
}

