package service;

import org.example.model.Client;
import org.example.Validari.ClientValidator;
import org.example.Validari.Validator;
import org.example.Validari.ValidatorException;
import org.example.repository.ClientDb;
import org.example.service.ClientService;
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

class ClientServiceTest implements Validator<Client> {

    ClientValidator clientValidator = new ClientValidator();

    IRepository<Long, Client> getClientRepository(){
        InMemoryRepository<Long,Client> testRepository=new InMemoryRepository<>(clientValidator);
        testRepository.save(new Client("1987654345678", "Marinescu", "Octavian", "Str.Plopilor", 1997));
        return testRepository;
    }

    ClientValidator getClientValidator(){
        ClientValidator clientValidator=new ClientValidator();
        return clientValidator;
    }

    ClientService getClientService(){
        IRepository<Long,Client> clientRepository=getClientRepository();
        ClientValidator clientValidator1=getClientValidator();
        ClientService clientService=new ClientService( clientRepository);
        return clientService;
    }


    @Test
    void addClient() {
        ClientService clientService = getClientService();
        clientService.addClient(new Client("1987654345678", "Marinescu", "Octavian", "Str.Plopilor", 1997));
        Set<Client> set=clientService.getAllC();
        assertEquals(1,set.size());
    }

    @Test
    void getAllC() {
        ClientService clientService = getClientService();
        Set <Client> set = clientService.getAllC();
        assertFalse(set.isEmpty());
    }

    @Test
    void deleteClient() {
        ClientService clientService = getClientService();
        clientService.addClient(new Client("1987654345678", "Marinescu", "Octavian", "Str.Plopilor", 1997));
        clientService.addClient(new Client("1965374585588", "Ionescu", "Marian", "Str.Plopilor", 1987));
        clientService.deleteClient("1");
        Set<Client> set=clientService.getAllC();
        assertEquals(1,set.size());
    }

    @Override
    public void validate(Client entity) throws ValidatorException {

    }
}