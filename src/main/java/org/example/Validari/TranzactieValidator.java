package org.example.Validari;

import org.example.Exceptie.Exceptie;
import org.example.model.Tranzactie;

public class TranzactieValidator implements Validator<Tranzactie>{
    @Override
    public void validate(Tranzactie entity) throws ValidatorException {
        String errors = "";

        if (entity.getId_book()<1) {
            errors += "Id-ul cartii nu poate fi mai mic decat 1. \n";
        }
        if (entity.getId_client()<1) {
            errors += "Id-ul clientului nu poate fi mai mic decat 1.\n";
        }
        if (entity.getNrBucati()<1) {
            errors += "Nu s-a realizat nici o tranzactie\n";
        }
        if (entity.getDataSiOra().isEmpty()) {
            errors += "Data si ora nu pot fi nule\n";
        }

        if (!errors.isEmpty()) {
            throw new Exceptie(errors);
        }
    }
}