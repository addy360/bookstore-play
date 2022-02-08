package models;

import io.ebean.Model;
import org.hibernate.validator.constraints.URL;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;


@Entity
public class Book extends Model {

    @Id
    public double id;

    @Constraints.Required
    public String title;

    @Constraints.Required
    @URL
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
