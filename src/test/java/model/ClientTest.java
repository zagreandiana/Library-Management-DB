package model;

import org.example.model.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void getCnp() {
        Client client = new Client("1987654345678", "Marinescu", "Octavian", "Str.Plopilor", 1997);
        assertEquals("1987654345678", client.getCnp(), "getCnp a returnat " + client.getCnp() + "in loc de 1987654345678");
    }

    @Test
    void getNume() {
        Client client = new Client("1987654345678", "Marinescu", "Octavian", "Str.Plopilor", 1997);
        assertEquals("Marinescu", client.getNume(), "getNume a returnat " + client.getNume() + "in loc de Marinescu");
    }

    @Test
    void getPrenume() {
        Client client = new Client("1987654345678", "Marinescu", "Octavian", "Str.Plopilor", 1997);
        assertEquals("Octavian", client.getPrenume(), "getPrenume a returnat " + client.getPrenume() + "in loc de Octavian");
    }

    @Test
    void getAdresa() {
        Client client = new Client("1987654345678", "Marinescu", "Octavian", "Str.Plopilor", 1997);
        assertEquals("Str.Plopilor", client.getAdresa(), "getAdresa a returnat " + client.getAdresa() + "in loc de Str.Plopilor");
    }

    @Test
    void getAnulNasterii() {
        Client client = new Client("1987654345678", "Marinescu", "Octavian", "Str.Plopilor", 1997);
        assertEquals(1997, client.getAnulNasterii(), "getAnulNasterii a returnat " + client.getAnulNasterii() + "in loc de 1997");
    }
}