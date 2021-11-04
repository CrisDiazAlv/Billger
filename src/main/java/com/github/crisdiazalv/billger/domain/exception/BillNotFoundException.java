package com.github.crisdiazalv.billger.domain.exception;

public class BillNotFoundException extends RuntimeException {

    public BillNotFoundException(String message) {
        super(message);
    }
}
