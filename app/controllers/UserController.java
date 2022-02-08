package controllers;

import io.ebean.DB;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import javax.inject.Inject;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

public class UserController extends Controller {
    User userService;
    public UserController(){
        userService = new User();
    }

    @Inject
    FormFactory formFactory;

    public Result index(Http.Request request){
        List<User> users = DB.find(User.class).findList();
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
        user.save();

        return redirect(routes.UserController.index()).flashing("success","user has been inserted successfully!");
    }

    public Result show(double id){
        User user = DB.find(User.class,id);
        return ok(views.html.users.show.render(user));
    }

    public Result edit(double id, Http.Request request){
        User user = DB.find(User.class, id);
        return ok(views.html.users.edit.render(user, request));
    }

    public Result update( double id, Http.Request request){
        User user = this.userService.findById(id);
        User u = formFactory.form(User.class).bindFromRequest(request).get();
        this.userService.update(user.id, u);

        return redirect(routes.UserController.index());
    }

    public Result delete(double id){
        DB.delete(User.class, id);

        return redirect(routes.UserController.index());
    }
}
