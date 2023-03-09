package org.example.service;
import org.example.model.Client;
import org.example.repository.IRepository;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientService {
    private IRepository<Long, Client> repository;

    public ClientService(IRepository<Long, Client> repository) {
        this.repository = repository;

    }
    public void addClient(Client client)  {
        repository.save(client);
    }


    // clasic method

//    public  Client  youngClient() {
//        List<Client> clients = (List<Client>) repository.findAll();
//
//        List<Client> client2 = new ArrayList<>();
//        for (Client client : clients) {
//            client2.add(client);
//        }
//        Collections.sort(client2, new Comparator<Client>() {
//            @Override
//            public int compare(Client o1, Client o2) {
//                return (-o1.getAnulNasterii()) - (-o2.getAnulNasterii());
//            }
//        });
//
//        Client clientT = client2.get(0);
//
//        return clientT;
//    }


    public Client youngClient() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .max(Comparator.comparingInt(Client::getAnulNasterii))
                .orElse(null);
    }

    public Client youngClinet2() {
        List<Client> clients = (List<Client>) repository.findAll();

        Set<Client> orderedClients = clients.stream().sorted(Comparator.comparing(Client::getAnulNasterii)).collect(Collectors.toCollection(LinkedHashSet::new));

        Iterator c = orderedClients.iterator();
        Client last = (Client) c.next();
        while (c.hasNext()) {
            last = (Client) c.next();
        }
        return last;
    }


    public Set<Client> getAllC() {
        Iterable<Client> clients = repository.findAll();
        return StreamSupport.stream(clients.spliterator(),false).collect(Collectors.toSet());
    }

    // clasic filterClientsByName

//    public Set<Client> filterClientsByName(String input) {
//        Iterable<Client> clients = repository.findAll();
//        Set<Client> filterClients = new HashSet<>();
//        clients.forEach(filterClients::add);
//        filterClients.removeIf(client -> !client.getNume().contains(input));
//        return filterClients;
//    }


    // filterClientsByName with stream

    public Set<Client> filterClientsByName(String input) {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .filter(client -> client.getNume().contains(input))
                .collect(Collectors.toSet());
    }

    public void deleteClient(String id) {
        this.repository.delete(Long.valueOf(id));


    }
    public void updateClient(long id, String cnp, String nume, String prenume, String adresa, int anulNasterii) {
        Client client = new Client(id,cnp, nume, prenume, adresa, anulNasterii);
        this.repository.update(client);

    }


}
