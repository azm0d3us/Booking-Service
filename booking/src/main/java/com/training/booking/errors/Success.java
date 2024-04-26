package com.training.booking.errors;

import lombok.Data;

@Data
public class Success extends Exception {

    private String message = "La tua eccezione sta funzionando.";

    public Success() {
        super();
    }

    public Success(String message) {
        super(message);
        this.message= message;
    }

    public Success(String message, Throwable innerException){
        super(message, innerException);
        this.message= message;
    }

}
