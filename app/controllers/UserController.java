package controllers;

import actionMiddlewares.AuthMiddleware;
import io.ebean.DB;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import javax.inject.Inject;
import java.util.List;

@With(AuthMiddleware.class)
public class UserController extends Controller {

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
        Form<User> userForm = formFactory.form(User.class);
        User user = DB.find(User.class, id);
        return ok(views.html.users.edit.render(user, request, userForm));
    }

    public Result update( double id, Http.Request request){
        User user = DB.find(User.class,id);
        Form<User> userForm = formFactory.form(User.class).bindFromRequest(request);
        if(userForm.hasErrors()){
            return badRequest(views.html.users.edit.render(user,request,userForm));
        }
        User u = formFactory.form(User.class).bindFromRequest(request).get();
       user.fullname = u.fullname;
       user.username = u.username;
       user.password = u.password;

       user.update();

        return redirect(routes.UserController.index()).flashing("success","user updated successfully");
    }

    public Result delete(double id){
        DB.delete(User.class, id);
        return redirect(routes.UserController.index());
    }
}
