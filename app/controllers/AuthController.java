package controllers;

import io.ebean.DB;
import models.User;
import models.helper.UserObj;
import org.mindrot.jbcrypt.BCrypt;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;


import javax.inject.Inject;
import java.util.List;


public class AuthController extends Controller {
    @Inject
    FormFactory formFactory;

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
                .withSession(request
                        .session()
                        .removing("uid"));
    }
}
