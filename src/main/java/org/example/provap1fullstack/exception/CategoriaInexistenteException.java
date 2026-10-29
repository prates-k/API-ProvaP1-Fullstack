package org.example.provap1fullstack.exception;

public class CategoriaInexistenteException extends RuntimeException {
    public CategoriaInexistenteException(String message) {
        super(message);
    }
}