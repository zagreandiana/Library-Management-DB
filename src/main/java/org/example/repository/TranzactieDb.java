package org.example.repository;

import org.example.Validari.ValidatorException;
import org.example.model.Tranzactie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TranzactieDb implements IRepository<Long,Tranzactie>{
    public static final String URL = System.getProperty("url");
    public static final String USER_NAME = System.getProperty("username");
    public static final String PASSWORD = System.getProperty("password");


    public TranzactieDb() {
    }


    @Override
    public Optional<Tranzactie> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public Iterable<Tranzactie> findAll() {
            List<Tranzactie> transactions = new ArrayList<>();

            try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

                 PreparedStatement preparedStatement = connection.prepareStatement("select * from public.tranzactie");
                 ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {
                    int id_book = resultSet.getInt("id_book");
                    int id_client = resultSet.getInt("id_client");
                    int nrBucati = resultSet.getInt("nrBucati");
                    String dataSiOra = resultSet.getString("dataSiOra");


                    Tranzactie tranzactie = new Tranzactie( id_book, id_client, nrBucati, dataSiOra);
                    transactions.add(tranzactie);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return (Iterable<Tranzactie>) transactions;

    }



    public Optional<Tranzactie> save(Tranzactie entity) throws ValidatorException {

        int id_book = entity.getId_book();
        int id_client = entity.getId_client();
        int nrBucati = entity.getNrBucati();
        String dataSiOra = entity.getDataSiOra();
        Tranzactie tranzactie =  new Tranzactie(id_book, id_client, nrBucati, dataSiOra);

        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into public.tranzactie (id_book, id_client, nrBucati, dataSiOra ) values (?, ?, ?, ?)");
            preparedStatement.setInt(1, tranzactie.getId_book());
            preparedStatement.setInt(2, tranzactie.getId_client());
            preparedStatement.setInt(3, tranzactie.getNrBucati());
            preparedStatement.setString(4, tranzactie.getDataSiOra());
            preparedStatement.executeUpdate();
        }
        catch (SQLException | ValidatorException e){
            e.printStackTrace();
        }
        return Optional.empty();

    }


    @Override
    public Optional<Tranzactie> delete(Long id) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("delete from public.tranzactie where id =?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }



    public Optional<Tranzactie> update(Tranzactie entity) throws ValidatorException {
        Long id = entity.getId();
        int id_book = entity.getId_book();
        int id_client = entity.getId_client();
        int nrBucati = entity.getNrBucati();
        String dataSiOra = entity.getDataSiOra();
        Tranzactie tranzactie =  new Tranzactie(id, id_book, id_client, nrBucati, dataSiOra);
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("update public.tranzactie set id_book =?, id_client=?, nrBucati=?, dataSiOra=? where id =?");
        ) {
            preparedStatement.setInt(1, tranzactie.getId_book());
            preparedStatement.setInt(2, tranzactie.getId_client());
            preparedStatement.setInt(3, tranzactie.getNrBucati());
            preparedStatement.setString(4, tranzactie.getDataSiOra());
            preparedStatement.setLong(5, tranzactie.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

}