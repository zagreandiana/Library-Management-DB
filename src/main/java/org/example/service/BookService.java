package org.example.service;
import org.example.Validari.ValidatorException;
import org.example.model.Book;
import org.example.repository.IRepository;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookService {
    private IRepository<Long, Book> repository;



    public BookService(IRepository<Long, Book> repository) {
        this.repository = repository;
    }

    public void addBook(Book book) throws ValidatorException {
        repository.save(book);
    }

    public void deleteBook(String id) {
        this.repository.delete(Long.valueOf(id));


    }

    public Set<Book> getAllB() {
        Iterable<Book> books = repository.findAll();
        return StreamSupport.stream(books.spliterator(),false).collect(Collectors.toSet());
    }


    public Set<Book> filterBooksByTitle(String input) {
        Iterable<Book> books = repository.findAll();

        Set<Book> filteredBooks = new HashSet<>();
        books.forEach(filteredBooks::add);
        filteredBooks.removeIf(book -> !book.getTitlu().contains(input));
        return filteredBooks;
    }


    public void updateBook(long id, String titlu,String autor, float pret, String editura) {
        Book book = new Book( id, titlu, autor, pret, editura);
        this.repository.update(book);

    }
    public  Book  mostExpensiveBook()  {
        List<Book> books = (List<Book>) repository.findAll();
        List<Book> book2 = new ArrayList<>();
//      parcurgere for clasic
//        for (Book book : books) {
//            book2.add(book);
//        }
//        lambda forEach
        books.forEach(book -> addBook(book));
//        sortare clasica

//        Collections.sort(book2, new Comparator<Book>() {
//            @Override
//            public int compare(Book o1, Book o2) {
//                return (int) ((-o1.getPret()) - (-o2.getPret()));
//            }
//        });



        book2.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return (int) (o2.getPret() - o1.getPret());
            }
        });

        Book bookB = book2.get(0);
        System.out.println("Most expensive book is" + bookB);

        return bookB;
    }

    // increase price clasic

//    public Set<Book> getListaCartiScumpite(float pragValoare, float procentaj)  {
//
//        Set<Book> listaFinala = new HashSet<>();
//        for (Book book : this.getAllB()) {
//            if (book.getPret() > pragValoare) {
//               float noulPret = book.getPret() + (book.getPret() * procentaj) / 100;
//                book.setPret(noulPret);
//            }
//            listaFinala.add(book);
//        }
//        return listaFinala;
//    }


    // increase price with stream
    public Set<Book> getListaCartiScumpite(float pragValoare, float procentaj) {
        return this.getAllB().stream()
                .filter(book -> book.getPret() > pragValoare)
                .peek(book -> book.setPret(book.getPret() + (book.getPret() * procentaj) / 100))
                .collect(Collectors.toSet());
    }


}


