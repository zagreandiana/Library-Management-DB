package org.example.Validari;

public interface Validator<T>  {

    void validate(T entity) throws ValidatorException;
}
