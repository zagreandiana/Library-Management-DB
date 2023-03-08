package org.example.service;
import org.example.model.Tranzactie;
import org.example.repository.IRepository;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class TranzactieService {


    private IRepository<Long, Tranzactie> repository;

    public TranzactieService(IRepository<Long, Tranzactie> repository) {
        this.repository = repository;
    }

    public void addTranzactie(Tranzactie tranzactie) {
        // verificare id-uri entitati book si client
        repository.save(tranzactie);
    }

    public Set<Tranzactie> getAllT() {
        Iterable<Tranzactie> tranzactions = repository.findAll();
        return StreamSupport.stream(tranzactions.spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Tranzactie> filterTranzactieByNrPieces(int input) {
        Iterable<Tranzactie> tranzactions = repository.findAll();

        Set<Tranzactie> filterTranzactions = new HashSet<>();
        tranzactions.forEach(filterTranzactions::add);
        filterTranzactions.removeIf(tranzactie -> tranzactie.getNrBucati() != input);
        return filterTranzactions;
    }

    public void deleteTranzactie(String id) {
        this.repository.delete(Long.valueOf(id));
    }

    public void updateTranzactie(long id, int id_book, int id_client, int nrBucati, String dataSiOra) {
        Tranzactie tranzactie = new Tranzactie(id, id_book, id_client, nrBucati, dataSiOra);
        this.repository.update(tranzactie);

    }

    // the first classic version for MostActiveClient method

//    public Integer MostActiveClient(){
//        List<Integer> listOfClientIds = new ArrayList<>();
//        List<Tranzactie> tranzaction = (List<Tranzactie>) repository.findAll();
//        for (Tranzactie tranzactie: tranzaction) {
//            listOfClientIds.add(tranzactie.getId_client());
//        }
//        Integer id = listOfClientIds.stream()
//                .reduce(BinaryOperator.maxBy((o1, o2) -> Collections.frequency(listOfClientIds, o1) -
//                        Collections.frequency(listOfClientIds, o2))).orElse(null);
//
//        return id;
//    }


    // second version for MostActiveClient method

//    public Integer MostActiveClient(){
//        List<Integer> listOfClientIds = new ArrayList<>();
//        List<Tranzactie> tranzaction = (List<Tranzactie>) repository.findAll();
//        for (Tranzactie tranzactie: tranzaction) {
//            listOfClientIds.add(tranzactie.getId_client());
//        }
//        Integer id = listOfClientIds.stream()
//                .reduce(BinaryOperator.maxBy(Comparator.comparingInt(o -> Collections.frequency(listOfClientIds, o))))
//                .orElse(null);
//
//        return id;
//    }


    // the third advanced version for MostActiveClient method
    public Integer MostActiveClient() {
        List<Tranzactie> transactions = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        Map<Integer, Long> transactionCounts = transactions.stream()
                .collect(Collectors.groupingBy(Tranzactie::getId_client, Collectors.counting()));

        return transactionCounts.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // classic version

//    public List<Tranzactie> getListaTranzactiiPeInterval(String inceputInterval, String sfarsitInterval) throws ParseException {
//        List<Tranzactie> getListaTranzactiiPeInterval = new ArrayList<>();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        inceputInterval = inceputInterval.replace("~", " ");
//        sfarsitInterval = sfarsitInterval.replace("~", " ");
//        LocalDateTime DeLa = LocalDateTime.parse(inceputInterval, formatter);
//        LocalDateTime PanaLa = LocalDateTime.parse(sfarsitInterval, formatter);
//        for (Tranzactie tranzactie : this.getAllT()) {
//            LocalDateTime tranzactieDateTime = LocalDateTime.parse(tranzactie.getDataSiOra(), formatter);
//            if (tranzactieDateTime.equals(DeLa) || tranzactieDateTime.isAfter(DeLa) && tranzactieDateTime.isBefore(PanaLa)) {
//                getListaTranzactiiPeInterval.add(tranzactie);
//            }
//
//        }
//        return getListaTranzactiiPeInterval;
//
//    }


    // version with lambda
    public List<Tranzactie> getListaTranzactiiPeInterval(String inceputInterval, String sfarsitInterval) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        inceputInterval = inceputInterval.replace("~", " ");
        sfarsitInterval = sfarsitInterval.replace("~", " ");
        LocalDateTime DeLa = LocalDateTime.parse(inceputInterval, formatter);
        LocalDateTime PanaLa = LocalDateTime.parse(sfarsitInterval, formatter);

        return this.getAllT().stream()
                .filter(tranzactie -> {
                    LocalDateTime tranzactieDateTime = LocalDateTime.parse(tranzactie.getDataSiOra(), formatter);
                    return tranzactieDateTime.isEqual(DeLa) || (tranzactieDateTime.isAfter(DeLa) && tranzactieDateTime.isBefore(PanaLa));
                })
                .collect(Collectors.toList());
    }
}
