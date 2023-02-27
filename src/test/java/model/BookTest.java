package model;

import org.example.model.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void getTitlu() {
        Book book = new Book("Securitatea", "Anton Ionescu", 23, "Paralela45");
        assertEquals("Securitatea", book.getTitlu(), "getTitlu a returnat " + book.getTitlu() + " in loc de Securitatea.  ");
    }

    @Test
    void getAutor() {
        Book book = new Book("Securitatea", "Anton Ionescu", 23, "Paralela45");
        assertEquals("Anton Ionescu", book.getAutor(), "getAutor a returnat " + book.getAutor() + " in loc de Anton Ionescu.  ");
    }


    @Test
    void getPret() {
        Book book = new Book("Securitatea", "Anton Inescu", 23, "Paralela45");
        assertEquals(23, book.getPret(), "getPret a returnat " + book.getPret() + " in loc de 23.  ");
    }



    @Test
    void getEditura() {
        Book book = new Book("Securitatea", "Anton Inescu", 23, "Paralela45");
        assertEquals("Paralela45", book.getEditura(), "getEditura a returnat " + book.getEditura() + " in loc de Paralela45.  ");
    }

}