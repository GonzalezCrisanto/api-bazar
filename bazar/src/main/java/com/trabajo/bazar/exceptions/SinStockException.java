package com.trabajo.bazar.exceptions;


public class SinStockException extends Exception{

    public SinStockException() {
    }

    public SinStockException(String msg) {
        super(msg);
    }
}
