package model.Validari;

import org.example.Exceptie.Exceptie;
import org.example.model.Tranzactie;
import org.example.Validari.TranzactieValidator;
import org.example.Validari.Validator;
import org.example.Validari.ValidatorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TranzactieValidatorTest implements Validator<Tranzactie> {

    @Override
    public void validate(Tranzactie entity) throws ValidatorException {

    }

    @Test
    void validate() {

        TranzactieValidator tranzactieValidator = new TranzactieValidator();

        Tranzactie t1 = new Tranzactie(0, 2, 2, "2022-05-02 00:00");
        try {
            tranzactieValidator.validate(t1);
            fail();
        } catch (Exceptie e) {
            assertEquals("Id-ul cartii nu poate fi mai mic decat 1. \n", e.getMessage());
        }


        Tranzactie t2 = new Tranzactie(20, 0, 2, "2022-05-02 00:00");
        try {
            tranzactieValidator.validate(t2);
            fail();
        } catch (Exceptie e) {
            assertEquals("Id-ul clientului nu poate fi mai mic decat 1.\n" , e.getMessage());
        }

        Tranzactie t3 = new Tranzactie(20, 1, 0, "2022-05-02 00:00");
        try {
            tranzactieValidator.validate(t3);
            fail();
        } catch (Exceptie e) {
            assertEquals("Nu s-a realizat nici o tranzactie\n" , e.getMessage());
        }

        Tranzactie t4 = new Tranzactie(20, 1, 2, "");
        try {
            tranzactieValidator.validate(t4);
            fail();
        } catch (Exceptie e) {
            assertEquals("Data si ora nu pot fi nule\n" , e.getMessage());
        }


    }
}