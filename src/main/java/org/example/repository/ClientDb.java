package org.example.repository;

import org.example.Validari.ValidatorException;
import org.example.model.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDb implements IRepository<Long,Client>{
    public static final String URL = System.getProperty("url");
    public static final String USER_NAME = System.getProperty("username");
    public static final String PASSWORD = System.getProperty("password");


    public ClientDb() {
    }


    @Override
    public Optional<Client> findOne(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Iterable<Client> findAll() {
            List<Client> clients = new ArrayList<>();
            try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

                 PreparedStatement preparedStatement = connection.prepareStatement("select * from public.client");
                 ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String cnp = resultSet.getString("cnp");
                    String nume = resultSet.getString("nume");
                    String prenume = resultSet.getString("prenume");
                    String adresa = resultSet.getString("adresa");
                    int anulNasterii = resultSet.getInt("anulNasterii");


                    Client client = new Client( id, cnp, nume, prenume, adresa, anulNasterii);
                    clients.add(client);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return (Iterable<Client>) clients;

    }


    public Optional<Client> save(Client entity) throws ValidatorException {

        String cnp = entity.getCnp();
        String nume = entity.getNume();
        String prenume = entity.getPrenume();
        String adresa = entity.getAdresa();
        int anulNasterii = entity.getAnulNasterii();
        Client client =  new Client(cnp, nume, prenume, adresa, anulNasterii);

        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into public.client (cnp, nume, prenume, adresa, anulNasterii ) values (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, client.getCnp());
            preparedStatement.setString(2, client.getNume());
            preparedStatement.setString(3, client.getPrenume());
            preparedStatement.setString(4, client.getAdresa());
            preparedStatement.setInt(5, client.getAnulNasterii());
            preparedStatement.executeUpdate();
        }
        catch (SQLException | ValidatorException e){
            e.printStackTrace();
        }
        return Optional.empty();

    }


    @Override
    public Optional<Client> delete(Long id) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("delete from public.client where id =?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }



    public Optional<Client> update(Client entity) throws ValidatorException {
        Long id = entity.getId();
        String cnp = entity.getCnp();
        String nume = entity.getNume();
        String prenume = entity.getPrenume();
        String adresa = entity.getAdresa();
        int anulNasterii = entity.getAnulNasterii();
        Client client =  new Client(id, cnp, nume, prenume, adresa, anulNasterii);
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("update public.client set cnp=?, nume =?, prenume=?, adresa=?, anulNasterii=? where id =?");
        ) {
            preparedStatement.setString(1, client.getCnp());
            preparedStatement.setString(2, client.getNume());
            preparedStatement.setString(3, client.getPrenume());
            preparedStatement.setString(4, client.getAdresa());
            preparedStatement.setInt(5, client.getAnulNasterii());
            preparedStatement.setLong(6, client.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}