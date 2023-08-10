package com.lukeonuke.web;

import lombok.Getter;

import java.io.IOException;

@Getter
public class VerificationException extends IOException {
    private final int statusCode;

    public VerificationException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
