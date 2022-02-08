package controllers;

import models.User;
import play.data.Form;
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
        Form<User> userForm = formFactory.form(User.class);
        return ok(views.html.users.create.render(request, userForm));
    }

    public Result store(Http.Request request){
        Form<User> userForm = formFactory.form(User.class).bindFromRequest(request);
        if(userForm.hasErrors()){
           return badRequest(views.html.users.create.render(request, userForm));
        }
        User user = userForm.get();
        this.userService.save(user);

        return redirect(routes.UserController.index()).flashing("success","user has been inserted successfully!");
    }

    public Result show(double id){
        User user = this.userService.findById(id);
        return ok(views.html.users.show.render(user));
    }

    public Result edit(double id, Http.Request request){
        User user = this.userService.findById(id);
        return ok(views.html.users.edit.render(user, request));
    }

    public Result update( double id, Http.Request request){
        User user = this.userService.findById(id);
        User u = formFactory.form(User.class).bindFromRequest(request).get();
        this.userService.update(user.id, u);

        return redirect(routes.UserController.index());
    }

    public Result delete(double id){
        this.userService.delete(id);

        return redirect(routes.UserController.index());
    }
}
