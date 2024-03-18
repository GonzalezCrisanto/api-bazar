package com.trabajo.bazar.exceptions;


public class ProductoNoEncontradoException extends Exception{

    
    public ProductoNoEncontradoException() {
    }

    
    public ProductoNoEncontradoException(String msg) {
        super(msg);
    }
}
