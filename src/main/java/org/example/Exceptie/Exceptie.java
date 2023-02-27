package org.example.Exceptie;

public class Exceptie extends RuntimeException {
    public Exceptie(String message) { super(message);}
    public Exceptie(String message, Throwable cause) { super(message, cause);}
    public Exceptie(Throwable cause) { super(cause);}
}
