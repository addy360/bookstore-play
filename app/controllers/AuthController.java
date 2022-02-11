package controllers;

import actionMiddlewares.LoginMiddleware;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.ebean.DB;
import io.ebean.Ebean;
import models.Book;
import models.User;
import models.helper.UserObj;
import org.mindrot.jbcrypt.BCrypt;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.With;


import javax.inject.Inject;
import java.util.List;


public class AuthController extends Controller {
    @Inject
    FormFactory formFactory;

    @With(LoginMiddleware.class)
    public Result login(Http.Request request){
        Form<UserObj> userObjForm = formFactory.form(UserObj.class);
        return ok(views.html.auth.login.render(userObjForm, request));
    }

    public Result postLogin(Http.Request request){
        Form<UserObj> userObjForm = formFactory.form(UserObj.class).bindFromRequest(request);
        if(userObjForm.hasErrors()){
            return badRequest(views.html.auth.login.render(userObjForm,request));
        }
        UserObj userData = userObjForm.get();
        List<User> users = DB.filter(User.class)
                .eq("username",userData.username)
                .maxRows(1)
                .filter(DB
                        .find(User.class)
                        .findList());
        if(users.size() < 1){
            userObjForm = userObjForm.withError("username","Invalid credentials");
            return badRequest(views.html.auth.login.render(userObjForm,request));
        }

        User u = users.get(0);
        boolean passVerify = BCrypt.checkpw(userData.password, u.password);
        if(!passVerify){
            userObjForm = userObjForm.withError("username","Invalid credentials");
            return badRequest(views.html.auth.login.render(userObjForm,request));
        }


        return redirect(routes.BooksController.index()).addingToSession(request,"uid",u.id.toString());
    }

    public Result logout(Http.Request request){

        return  redirect(routes.AuthController.login())
                .withNewSession();
    }

    public Result getUsers (Http.Request request){
        List<User> users = Ebean.find(User.class).fetch("books","id,title,thumbnail").findList();
        return created(Ebean.json().toJson(users)).as("application/json");
    }
}
