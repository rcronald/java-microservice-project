package com.rcronald.ms.jsmproject.exception;

public class UnSupportedFieldException extends RuntimeException  {
    public UnSupportedFieldException(String key) {
        super("Field " + key + " is missing.");
    }
}
