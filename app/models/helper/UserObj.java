package models.helper;

import play.data.validation.Constraints;

public class UserObj {

    @Constraints.Required
    public String username;

    @Constraints.Required
    public  String password;

    public UserObj(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserObj(){}
}
