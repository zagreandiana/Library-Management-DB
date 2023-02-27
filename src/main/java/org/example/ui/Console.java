package org.example.ui;
import org.example.Exceptie.Exceptie;
import org.example.Validari.ValidatorException;
import org.example.model.Book;
import org.example.model.Client;
import org.example.model.Tranzactie;
import org.example.service.BookService;
import org.example.service.ClientService;
import org.example.service.TranzactieService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class Console {

    private BookService bookService;
    private ClientService clientService;
    private TranzactieService tranzactieService;


    Scanner scanner = new Scanner(System.in);

    public Console(BookService bookService, ClientService clientService, TranzactieService tranzactieService) {
        this.bookService = bookService;
        this.clientService = clientService;
        this.tranzactieService = tranzactieService;


    }

    public void runConsole() throws SQLException {
        while (true) {
            this.printMeniu();

            String Option = scanner.next();
            if (Option.equals("x")) {
                break;
            }
            switch (Option) {
                case "1":
                    this.subMenu1();
                    String target = scanner.next();
                    switch (target) {
                        case "1":
                            handleAddBooks();
                            break;
                        case "2":
                            handleAddClients();
                            break;
                        case "3":
                            handleAddTranzactions();
                            break;
                    }
                    break;
                case "2":
                    subMenu2();
                    String target2 = scanner.next();
                    switch (target2) {
                        case "1":
                            handlePrintAllBooks();
                            break;
                        case "2":
                            handlePrintAllClients();
                            break;
                        case "3":
                            handlePrintAllTranzactions();
                            break;
                    }
                    break;
                case "3":
                    this.subMenu3();
                    String target3 = scanner.next();
                    switch (target3) {
                        case "1":
                            handleUpdateBook();
                            break;
                        case "2":
                            handleUpdateClient();
                            break;
                        case "3":
                            handleUpdateTranzactie();
                            break;
                    }
                    break;
                case "4":
                    this.subMenu4();
                    String target4 = scanner.next();
                    switch (target4) {
                        case "1":
                            handleDeleteBook();
                            break;
                        case "2":
                            handleDeleteClients();
                            break;
                        case "3":
                            handleDeleteTranzactions();
                            break;
                    }
                    break;
                case "5":
                    this.subMenu5();
                    String target5 = scanner.next();
                    switch (target5) {
                        case "1":
                            handleCartiScumpite();
                            break;
                        case "2":
                            handleTheYoungestClient();
                            break;
                        case "3":
                            handleYC();
                            break;
                        case "4":
                            handleMostExpensiveBook();
                            break;
                        case "5":
                            handleAfisareTranzactiiPeInterval();
                            break;
                        case "6":
                            handleMostActiveClient();
                            break;
                    }
                    break;
                case "6":
                    this.subMenu6();
                    String target6 = scanner.next();
                    switch (target6) {
                        case "1":
                            handleFilterBooks();
                            break;
                        case "2":
                            handleFilterClients();
                            break;
                        case "3":
                            handleFilterTranzactions();
                            break;
                    }


                default:
                    System.out.println("Invalid option");
            }
            break;
        }

    }

    private void handleMostExpensiveBook()  {
        bookService.mostExpensiveBook();

    }

    private void handleMostActiveClient(){
       Integer maxOccurredElement = tranzactieService.MostActiveClient();
       Client clientCautat1 = new Client();
       Set<Client> clients = clientService.getAllC();
       for (Client client : clients){
           if(client.getId().equals(maxOccurredElement.longValue())) {
               clientCautat1 = client;
           }
       }
       System.out.println(clientCautat1);
    }

    private void handleYC() {
        Client client2 = clientService.youngClinet2();
        System.out.println(client2);
    }

    private void handleTheYoungestClient() {

        Client clientT = clientService.youngClient();
        System.out.println("Cel mai tanar client este:" + clientT);
    }


    private void handleDeleteClients() {
        try {
            System.out.println("id client");
            String id = scanner.next();
            this.clientService.deleteClient(id);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleDeleteTranzactions() {
        try {
            System.out.println("id tranzactie");
            String id = scanner.next();
            this.tranzactieService.deleteTranzactie(id);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleDeleteBook() {
        try {
            System.out.println("id book");
            String id = scanner.next();
            this.bookService.deleteBook(id);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }


    private void handleFilterBooks() {
        System.out.println("filtrare carti dupa titlurile care contin inputul:");
        String input = scanner.next();
        Set<Book> books = bookService.filterBooksByTitle(input);
        books.stream().forEach(System.out::println);
    }

    private void handleFilterClients() {
        System.out.println("filtrare clienti dupa numele care contin inputul:");
        String input = scanner.next();
        Set<Client> clients = clientService.filterClientsByName(input);
        clients.stream().forEach(System.out::println);
    }

    private void handleFilterTranzactions() {
        System.out.println("filtrare tranzactii dupa nr de bucati, nr:");
        int input = Integer.parseInt(scanner.next());
        Set<Tranzactie> tranzactions = tranzactieService.filterTranzactieByNrPieces(input);
        tranzactions.stream().forEach(System.out::println);
    }

    private void handlePrintAllBooks()  {
        Set<Book> books = bookService.getAllB();
        books.stream().forEach(System.out::println);
    }

    private void handlePrintAllClients() {
        Set<Client> clients = clientService.getAllC();
        clients.stream().forEach(System.out::println);
    }

    private void handlePrintAllTranzactions() {
        Set<Tranzactie> tranzactions = tranzactieService.getAllT();
        tranzactions.stream().forEach(System.out::println);
    }

//    private void handleAddBooks() {
//        while (true) {
//            Book book = readBook();
////            if (book == null || book.getId() < 0) {
//            if (book == null || book.getId() < 0) {
//
//                break;
//            }
//            try {
//                bookService.addBook(book);
//            } catch (ValidatorException | ParserConfigurationException | IOException | TransformerException |
//                     SAXException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private void handleAddBooks() {
        while (true) {
            Book book = readBook();

            try {
                bookService.addBook(book);
            } catch (ValidatorException  e) {
                e.printStackTrace();
            }
        }
    }

    private void handleUpdateBook() {
        try {
            System.out.println("Introduceti id-ul cartii: ");
            long id = Long.parseLong(this.scanner.next());


            System.out.println("Introduceti titlul cartii: ");
            String titlu = this.scanner.next();

            System.out.println("Introduceti autorul cartii: ");
            String autor = this.scanner.next();


            System.out.println("Introduceti pretul: ");
            float pret = Float.parseFloat(this.scanner.next());

            System.out.println("Introduceti editura: ");
            String editura = this.scanner.next();

            this.bookService.updateBook(id, titlu, autor, pret, editura);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleUpdateClient() {
        try {
            System.out.println("Introduceti id-ul clientului: ");
            long id = Long.parseLong(this.scanner.next());

            System.out.println("Introduceti CNP-ul clientului:  ");
            String cnp = scanner.next();

            System.out.println("Introduceti numele: ");
            String numele = scanner.next();

            System.out.println("Introduceti prenumele: ");
            String prenumele = scanner.next();

            System.out.println("Introduceti adresa dumneavoastra curenta: ");
            String adresa = scanner.next();

            System.out.println("Introduceti anul nasterii: ");
            int anulNasterii = Integer.parseInt(scanner.next());


            this.clientService.updateClient(id, cnp, numele, prenumele, adresa, anulNasterii);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleUpdateTranzactie() {
        try {
            System.out.println("Introduceti id tranzactiei:  ");
            long id = Long.parseLong(scanner.next());

            System.out.println("Introduceti id_book-ul: ");
            int id_book = Integer.parseInt(scanner.next());

            System.out.println("Introduceti id_clientului: ");
            int id_client = Integer.parseInt(scanner.next());

            System.out.println("Introduceti nr de bucati: ");
            int nrBucati = Integer.parseInt(scanner.next());

            System.out.println("Introduceti data si ora tranzactiei in format yyyy-MM-dd HH:mm : ");
            String dataSiOra = scanner.next();

            this.tranzactieService.updateTranzactie(id, id_book, id_client, nrBucati, dataSiOra);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }


    private void handleAddClients() {
        while (true) {
            Client client = readClient();
            if (client == null || client.getId() < 0) {
                break;
            }
            try {
                clientService.addClient(client);
            } catch (ValidatorException e) {
                e.printStackTrace();

            }
        }
    }

    private void handleAddTranzactions() {
        while (true) {
            Tranzactie tranzactie = readTranzactie();
            if (tranzactie == null || tranzactie.getId() < 0) {
                break;
            }
            try {
                tranzactieService.addTranzactie(tranzactie);
            } catch (ValidatorException e) {
                e.printStackTrace();

            }
        }
    }

    private Client readClient() {
        System.out.println("Read client {id, cnp, nume, prenume, adresa, anulNasterii}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());// ...
            String cnp = bufferRead.readLine();
            String nume = bufferRead.readLine();
            String prenume = bufferRead.readLine();
            String adresa = bufferRead.readLine();
            int anulNasterii = Integer.parseInt(bufferRead.readLine());
            Client client = new Client(cnp, nume, prenume, adresa, anulNasterii);
            client.setId(id);
            return client;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Book readBook() {
        System.out.println("Read book {id, titlu, autor, pret, editura}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());// ...
            String titlu = bufferRead.readLine();
            String autor = bufferRead.readLine();
            float pret = Float.parseFloat(bufferRead.readLine());
            String editura = bufferRead.readLine();// ...
            Book book = new Book(titlu, autor, pret, editura);
            book.setId(id);
            return book;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Tranzactie readTranzactie() {
        System.out.println("Read tranzaction {id, id_book, id_client, nrBucati, dataSiOra}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());// ...
            int id_book = Integer.parseInt(bufferRead.readLine());
            int id_client = Integer.parseInt(bufferRead.readLine());
            int nrBucati = Integer.parseInt(bufferRead.readLine());
            String dataSiOra = bufferRead.readLine();// ...
            Tranzactie tranzactie = new Tranzactie(id_book, id_client, nrBucati, dataSiOra);
            tranzactie.setId(id);
            return tranzactie;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void handleCartiScumpite() {
        try {
            System.out.println("Introduceti prajul de valoare peste care vor fi scumpite cartile: ");
            float pragValoare = Float.parseFloat(this.scanner.next());
            System.out.println("Introduceti procentajul cu care vor fi scumpite cartile %: ");
            float procentaj = Float.parseFloat(this.scanner.next());
            System.out.println(bookService.getListaCartiScumpite(pragValoare, procentaj));

        } catch (Exceptie e) {
            System.out.println(e.getMessage());

        }
    }



    private void handleAfisareTranzactiiPeInterval() {
        try{
            System.out.println("Introduceti data la care incepe intervalul format yyyy-MM-dd~HH:mm: ");
            String inceputInterval = scanner.next();
            System.out.println("Introduceti data la care se incheie intervalul yyyy-MM-dd~HH:mm: ");
            String sfarsitInterval = scanner.next();
            for (Tranzactie tranzactiiPeInterval : this.tranzactieService.getListaTranzactiiPeInterval(inceputInterval, sfarsitInterval)){
            System.out.println(tranzactiiPeInterval);
        }}catch(ParseException ex){
            ex.printStackTrace();

        }


    }


        private void printMeniu () {
            System.out.println(
                    "1-Add book/cliet/tranzaction\n" +
                            "2-Print book/client/tranzaction\n" +
                            "3-Update book/client/tranzaction\n" +
                            "4-Delete book/client/tranzaction\n" +
                            "5-Raports\n" +
                            "6-Filter book/client/tranzaction\n" +
                            "x-Exit"
            );
        }

        private void subMenu1 () {
            System.out.println("1-Add book\n" +
                    "2-Add client\n" +
                    "3-Add tranzaction\n" +
                    "x-Exit");
        }

        private void subMenu2 () {
            System.out.println("1-Show books\n" +
                    "2-Show clients\n" +
                    "3-Show tranzactions\n" +
                    "x-Exit");
        }

        private void subMenu3 () {
            System.out.println("1-Update book \n" +
                    "2-Update client\n" +
                    "3-Update tranzaction\n" +
                    "x-Exit");
        }

        private void subMenu4 () {
            System.out.println("1-Delete book\n" +
                    "2-Delete client\n" +
                    "3-Delete transaction\n" +
                    "x-Exit");
        }

        private void subMenu5 () {
            System.out.println("1-Increase price book\n" +
                    "2-Cel mai tanar client\n" +
                    "3-Cel mai tanar client Iterator\n" +
                    "4-Most expensive book\n" +
                    "5-Tranzactii dintr-un interval dat\n" +
                    "6-Cel mai activ client\n" +
                    "x-Exit");
        }

        private void subMenu6 () {
            System.out.println("1-Filter Book" + "\n" +
                    "2-Filter Client" + "\n" +
                    "3-Filter Tranzaction" + "\n" +
                    "x-Exit");
        }



}
