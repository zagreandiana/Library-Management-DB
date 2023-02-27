package model.Validari;

import org.example.Exceptie.Exceptie;
import org.example.model.Book;
import org.example.Validari.BookValidator;
import org.example.Validari.Validator;
import org.example.Validari.ValidatorException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class BookValidatorTest implements Validator<Book> {

    @Override
    public void validate(Book entity) throws ValidatorException {

    }
    @Test
    void validate() {

//       IRepository<Long, Book> bookIRepository = new InMemoryRepository<>();
       BookValidator bookValidator = new BookValidator();

       Book b1 = new Book("", "Vlad Voicu", 23, "Paralela45");
       try {
           bookValidator.validate(b1);
           fail();
       } catch (Exceptie e){
           assertEquals("Numele titlului nu poate fi gol\n", e.getMessage());
       }

        Book b2 = new Book("Mara", "Vlad Voicu", 0, "Paralela45");
        try {
            bookValidator.validate(b2);
            fail();
        } catch (Exceptie e){
            assertEquals("Pretul nu poate fi zero\n", e.getMessage());
        }

        Book b3 = new Book("Mara", "Vlad Voicu", 234, "");
        try {
            bookValidator.validate(b3);
            fail();
        } catch (Exceptie e){
            assertEquals("Editura cartii nu poate fi gol\n", e.getMessage());
        }
    }
}