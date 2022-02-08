package models;

import io.ebean.Model;
import org.hibernate.validator.constraints.URL;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Book extends Model {

    @Id
    public double id;

    @Constraints.Required
    public String title;

    @Constraints.Required
    @URL
    public String thumbnail;



    public Book(String title, String thumbnail){

        this.title = title;
        this.thumbnail = thumbnail;
    }

    public Book(){}




}
