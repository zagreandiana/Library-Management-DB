package org.example.model;


import java.util.Objects;

public class Book extends BaseEntity<Long> {

    private String titlu;
    private String autor;
    private float pret;
    private String editura;



    public Book(long id, String titlu, String autor, float pret, String editura){
        super(id);
        this.titlu = titlu;
        this.autor = autor;
        this.pret = pret;
        this.editura = editura;

    }



    public Book(String titlu, String autor, float pret, String editura) {
        this.titlu = titlu;
        this.autor = autor;
        this.pret = pret;
        this.editura = editura;

    }
    public Book() {


    }


    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public String getEditura() {
        return editura;
    }

    public void setEditura(String editura) {
        this.editura = editura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Float.compare(book.getPret(), getPret()) == 0 && Objects.equals(getTitlu(), book.getTitlu()) && Objects.equals(getAutor(), book.getAutor()) && Objects.equals(getEditura(), book.getEditura());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitlu(), getAutor(), getPret(), getEditura());
    }

    @Override
    public String toString() {
        return "Book{" +
                "titlu='" + titlu + '\'' +
                ", autor='" + autor + '\'' +
                ", pret=" + pret +
                ", editura='" + editura + '\'' +
                '}';
    }
}
