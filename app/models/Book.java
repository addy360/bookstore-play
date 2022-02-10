package models;

import io.ebean.Finder;
import io.ebean.Model;
import org.hibernate.validator.constraints.URL;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Book extends Model {

    @Id
    public double id;

    @Constraints.Required
    public String title;

    @Constraints.Required
    @URL
    public String thumbnail;

    @ManyToOne
    public User user;



    public Book(String title, String thumbnail, User user){
        this.user = user;
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public static Finder<Double,Book> find = new Finder<>(Book.class);
    public Book(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
