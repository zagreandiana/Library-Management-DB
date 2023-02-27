package service;

import org.example.model.Tranzactie;
import org.example.Validari.TranzactieValidator;
import org.example.Validari.Validator;
import org.example.Validari.ValidatorException;
import org.example.repository.TranzactieDb;
import org.example.service.TranzactieService;
import org.junit.jupiter.api.Test;
import org.example.repository.IRepository;
import org.example.repository.InMemoryRepository;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TranzactieServiceTes implements Validator<Tranzactie> {

    TranzactieValidator tranzactieValidator = new TranzactieValidator();

    IRepository<Long, Tranzactie> getTranzactieRepository(){
        InMemoryRepository<Long,Tranzactie> testRepository=new InMemoryRepository<>(tranzactieValidator);
        testRepository.save(new Tranzactie(20, 1, 2, "2022-05-02 00:00"));
        return testRepository;
    }

    TranzactieValidator getTranzactieValidator(){
        TranzactieValidator tranzactieValidator=new TranzactieValidator();
        return tranzactieValidator;
    }

    TranzactieService getTranzactieService(){
        IRepository<Long,Tranzactie> tranzactieRepository=getTranzactieRepository();
        TranzactieValidator tranzactieValidator=getTranzactieValidator();
        TranzactieService tranzactieService=new TranzactieService( tranzactieRepository);
        return tranzactieService;
    }

    @Test
    void addTranzactie() throws SQLException, ParserConfigurationException, IOException, TransformerException, SAXException {
        TranzactieService tranzactieService = getTranzactieService();
        tranzactieService.addTranzactie(new Tranzactie(20, 1, 2, "2022-05-02 00:00"));
        Set<Tranzactie> set=tranzactieService.getAllT();
        assertEquals(1,set.size());
    }

    @Test
    void getAllT() {
        TranzactieService tranzactieService = getTranzactieService();
        Set <Tranzactie> set = tranzactieService.getAllT();
        assertFalse(set.isEmpty());
    }

    @Test
    void deleteTranzactie() throws SQLException, ParserConfigurationException, IOException, TransformerException, SAXException {
        TranzactieService tranzactieService = getTranzactieService();
        tranzactieService.addTranzactie(new Tranzactie(20, 2, 2, "2022-05-02 00:00"));
        tranzactieService.addTranzactie(new Tranzactie(30, 3, 1, "2022-05-01 00:00"));
        tranzactieService.deleteTranzactie("1");
        Set<Tranzactie> set=tranzactieService.getAllT();
        assertEquals(1,set.size());
    }

    @Override
    public void validate(Tranzactie entity) throws ValidatorException {

    }
}