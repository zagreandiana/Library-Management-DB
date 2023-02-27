package org.example.repository;

import org.example.Validari.ValidatorException;
import org.example.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDb implements IRepository<Long,Book>{
    public static final String URL = System.getProperty("url");
    public static final String USER_NAME = System.getProperty("username");
    public static final String PASSWORD = System.getProperty("password");


    public BookDb() {
    }

    @Override
    public Optional<Book> findOne(Long id) {

        return Optional.empty();
    }

    @Override
    public Iterable<Book> findAll() {
            List<Book> books = new ArrayList<>();

            try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement("select * from public.book");
                 ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {
                    String titlu = resultSet.getString("titlu");
                    String autor = resultSet.getString("autor");
                    float pret = resultSet.getInt("pret");
                    String editura = resultSet.getString("editura");

                    Book book = new Book( titlu, autor, pret, editura);
                    books.add(book);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return  books;


    }

    @Override
    public Optional<Book> save(Book entity) throws ValidatorException {
        String title = entity.getTitlu();
        String autor = entity.getAutor();
        float pret = entity.getPret();
        String editura = entity.getEditura();
        Book book =  new Book(title, autor, pret, editura);

        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into public.book (titlu, autor, pret, editura ) values (?, ?, ?, ?)");
//            preparedStatement.setLong(1, book.getId());
            preparedStatement.setString(1, book.getTitlu());
            preparedStatement.setString(2, book.getAutor());
            preparedStatement.setFloat(3, book.getPret());
            preparedStatement.setString(4, book.getEditura());



            preparedStatement.executeUpdate();
        }
        catch (SQLException | ValidatorException e){
            e.printStackTrace();
        }
        return Optional.empty();


    }

    @Override
    public Optional<Book> delete(Long id) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("delete from public.book where id =?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }




    public Optional<Book> update(Book entity) throws ValidatorException {
        Long id = entity.getId();
        String title = entity.getTitlu();
        String autor = entity.getAutor();
        float pret = entity.getPret();
        String editura = entity.getEditura();
        Book book =  new Book(id, title, autor, pret, editura);
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("update public.book set titlu =?, autor=?, pret=?, editura=? where id =?");
        ) {
            preparedStatement.setString(1, book.getTitlu());
            preparedStatement.setString(2, book.getAutor());
            preparedStatement.setFloat(3, book.getPret());
            preparedStatement.setString(4, book.getEditura());
            preparedStatement.setLong(5, book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

}