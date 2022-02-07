package models;


import java.util.ArrayList;
import java.util.Random;

public class User {
    public Double id;
    public String fullname;
    public String username;
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
}
