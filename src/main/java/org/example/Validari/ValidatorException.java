package org.example.Validari;

import org.example.Exceptie.Exceptie;

public class ValidatorException extends Exceptie {
    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorException(Throwable cause) {
        super(cause);
    }
}
