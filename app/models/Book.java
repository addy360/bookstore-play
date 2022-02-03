package models;

import java.util.ArrayList;


public class Book {
    public double id;
    public String title;
    public String thumbnail;

    ArrayList<Book> books = new ArrayList<>();

    public Book(String title, String thumbnail){

        this.title = title;
        this.thumbnail = thumbnail;
    }

    public Book(){}


    public ArrayList<Book> find(){
        return books;
    }

    public Book update(Book book){

        for (Book b : this.books){
            if(b.id == book.id){
                this.books.set(this.books.indexOf(book),book);
            }
        }
        return book;
    }

    public  Book findById(Double id){
        Book b = null;

        for (Book book : this.books) {
            if(book.id == id){
                b = book;
            }
        }

        return b;
    }

    public boolean findByIdAndDelete(Double id){

       return books.removeIf(book -> book.id == id);
    }

    public Book save(Book book){
        book.id = Math.random() ;
        books.add(book);
        return book;
    }
}
