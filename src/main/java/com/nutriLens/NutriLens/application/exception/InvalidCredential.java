package com.nutriLens.NutriLens.application.exception;

public class InvalidCredential extends ServiceException{
    public InvalidCredential(String message) {
        super(message);
    }
}
