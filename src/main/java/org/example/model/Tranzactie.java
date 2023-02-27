package org.example.model;

public class Tranzactie extends BaseEntity<Long> {

    private int id_book;
    private int id_client;
    private int nrBucati;
    private String dataSiOra;



    public Tranzactie(Long id, int id_book, int id_client, int nrBucati, String dataSiOra) {
        super(id);
        this.id_book = id_book;
        this.id_client = id_client;
        this.nrBucati = nrBucati;
        this.dataSiOra = dataSiOra;
    }
    public Tranzactie(int id_book, int id_client, int nrBucati, String dataSiOra) {

        this.id_book = id_book;
        this.id_client = id_client;
        this.nrBucati = nrBucati;
        this.dataSiOra = dataSiOra;


    }

    public Tranzactie() {

    }



    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setNrBucati(int nrBucati) {
        this.nrBucati = nrBucati;
    }

    public void setDataSiOra(String dataSiOra) {
        this.dataSiOra = dataSiOra;
    }

    public int getId_book() {
        return id_book;
    }

    public int getId_client() {
        return id_client;
    }

    public int getNrBucati() {
        return nrBucati;
    }

    public String getDataSiOra() {
        return dataSiOra;
    }




    @Override
    public String toString() {
        return "Tranzactie{" +
                "id_book=" + id_book +
                ", id_client=" + id_client +
                ", nrBucati=" + nrBucati +
                ", dataSiOra='" + dataSiOra + '\'' +
                '}';
    }
}


