package models;


public class User {
    public String fullname;
    public String username;
    public String password;

    public User(){}

    public User(String fullname, String username, String password) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
    }

}
