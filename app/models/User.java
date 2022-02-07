package models;

import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Random;


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

    public ArrayList<User> users = new ArrayList<>();

    public User(){}

    public User(String fullname, String username, String password) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
    }

    public User save(User user){
        user.id = new Random().nextDouble();
        this.users.add(user);
        return user;
    }

    public User findById(double id){
        User user = null;
        for (User u : users){
            if(u.id == id){
                user = u;
            }
        }
        return user;
    }

    public User update(double id, User user){
        for(User u: this.users){
            if(u.id ==id){
                user.id = u.id;
                this.users.set(this.users.indexOf(u),user);
            }
        }
        return user;
    }

    public boolean delete(double id){
       return this.users.removeIf(user -> user.id == id);
    }
}
