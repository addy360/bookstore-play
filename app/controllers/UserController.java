package controllers;

import models.User;
import play.data.FormFactory;
import play.mvc.*;

import javax.inject.Inject;
import java.util.ArrayList;

public class UserController extends Controller {
    User userService;
    public UserController(){
        userService = new User();
    }

    @Inject
    FormFactory formFactory;

    public Result index(Http.Request request){
        ArrayList<User> users = this.userService.users;
        return  ok(views.html.users.index.render(request, users));
    }

    public Result create(Http.Request request){
        return ok(views.html.users.create.render(request));
    }

    public Result store(Http.Request request){
        User user = formFactory.form(User.class).bindFromRequest(request).get();

        this.userService.save(user);

        return redirect(routes.UserController.index()).flashing("success","user has been inserted successfully!");
    }
}
