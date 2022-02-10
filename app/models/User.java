package models;

import io.ebean.Finder;
import io.ebean.Model;
import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
public class User extends Model {

    @Id
    public Double id;

    @Constraints.Required
    public String fullname;

    @Constraints.Required
    public String username;

    @Constraints.Required
    public String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    public List<Book> books;


    public User(){}

    public User(String fullname, String username, String password) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
    }

    public static Finder<Double,User> find = new Finder<>(User.class);

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public void save() {
        this.password = BCrypt.hashpw(this.password,BCrypt.gensalt());
        super.save();
    }
}
