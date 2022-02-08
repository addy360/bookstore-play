package models;

import io.ebean.Model;
import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;



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


    public User(){}

    public User(String fullname, String username, String password) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
    }

    @Override
    public void save() {
        this.password = BCrypt.hashpw(this.password,BCrypt.gensalt());
        super.save();
    }
}
