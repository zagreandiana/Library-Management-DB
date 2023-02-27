package model;

import org.example.model.Tranzactie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TranzactieTest {

    @Test
    void getId_book() {
        Tranzactie tranzactie = new Tranzactie(20, 1, 2, "2022-05-02 00:00");
        assertEquals(20, tranzactie.getId_book(), "getId_book a returnat " + tranzactie.getId_book() + "in loc de 1");
    }

    @Test
    void getId_client() {
        Tranzactie tranzactie = new Tranzactie(20, 1, 2, "2022-05-02 00:00");
        assertEquals(1, tranzactie.getId_client(), "getId_cleint a returnat " + tranzactie.getId_client() + "in loc de 1");
    }

    @Test
    void getNrBucati() {
        Tranzactie tranzactie = new Tranzactie(20, 1, 2, "2022-05-02 00:00");
        assertEquals(2, tranzactie.getNrBucati(), "getNrBucati a returnat " + tranzactie.getNrBucati() + "in loc de 2");
    }

    @Test
    void getDataSiOra() {
        Tranzactie tranzactie = new Tranzactie(20, 1, 2, "2022-05-02 00:00");
        assertEquals("2022-05-02 00:00", tranzactie.getDataSiOra(), "getDataSiOra a returnat " + tranzactie.getDataSiOra() + "in loc de 2022-05-02 00:00");
    }
}