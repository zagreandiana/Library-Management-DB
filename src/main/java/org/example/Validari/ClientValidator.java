package org.example.Validari;

import org.example.Exceptie.Exceptie;
import org.example.model.Client;

public class ClientValidator implements Validator<Client> {
    @Override
    public void validate(Client entity) throws ValidatorException {
        String errors = "";
        if (entity.getNume().isEmpty()) {
            errors += "Numele clientului nu poate fi gol\n";
        }
        if (entity.getPrenume().isEmpty()) {
            errors += "Prenumele clientului nu poate fi gol\n";
        }
        if (entity.getAnulNasterii() > 2022) {
            errors += "Anul nasterii nu poate fi mai mare de 2022\n";
        }
        if (entity.getAdresa().isEmpty()) {
            errors += "Campul adresei nu poate fi gol\n";
        }
        if (entity.getCnp().length() != 13) {
            errors += "CNP-ul trebuie sa contina 13 cifre\n";
        }

        if (!errors.isEmpty()) {
            throw new Exceptie(errors);
        }
    }
}


