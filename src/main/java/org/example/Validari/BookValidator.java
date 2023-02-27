package org.example.Validari;

import org.example.Exceptie.Exceptie;
import org.example.model.Book;

public class BookValidator implements Validator<Book> {
    public void validate(Book entity) throws ValidatorException {

        String errors = "";
        if (entity.getTitlu().isEmpty()) {
            errors += "Numele titlului nu poate fi gol\n";
        }
        if (entity.getPret() < 1) {
            errors += "Pretul nu poate fi zero\n";
        }

        if (entity.getEditura().isEmpty()) {
            errors += "Editura cartii nu poate fi gol\n";
        }
        if (!errors.isEmpty()) {
            throw new Exceptie(errors);
        }
    }



}

