package org.example;
import org.example.model.Book;
import org.example.model.Client;
import org.example.model.Tranzactie;
import org.example.repository.*;
import org.example.service.BookService;
import org.example.service.ClientService;
import org.example.service.TranzactieService;
import org.example.ui.Console;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {


        IRepository<Long, Book> bookRepository = new BookDb();
        BookService bookService = new BookService(bookRepository);

        IRepository<Long, Client> clientRepository = new ClientDb();
        ClientService clientService = new ClientService(clientRepository);


        IRepository<Long, Tranzactie> tranzactieRepository = new TranzactieDb();
        TranzactieService tranzactieService = new TranzactieService(tranzactieRepository);



        Console console = new Console(bookService, clientService, tranzactieService);
        try {
            console.runConsole();

        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();
            System.out.println("Unknown error");

        }



    }
}
