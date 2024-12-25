package com.spring_boot.learning.exceptions;

public class JournalNotFoundException extends RuntimeException {
    public JournalNotFoundException(String message){
        super(message);
    }
}
