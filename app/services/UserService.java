package services;

import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Http;

import javax.inject.Inject;
import java.util.List;

public class UserService {
    @Inject
    FormFactory formFactory;

    public List<User> getUsers(){
        return User.find.all();
    }

    public User saveUser(Http.Request request){
        Form<User> userForm = formFactory.form(User.class).bindFromRequest(request);
        if(userForm.hasErrors()){
            return null;
        }
        User user = userForm.get();
        user.save();

        return user;
    }
}
