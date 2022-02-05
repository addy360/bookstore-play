package controllers;

import models.User;
import play.data.FormFactory;
import play.mvc.*;

import javax.inject.Inject;

public class UserController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result index(Http.Request request){
        return  ok(views.html.users.index.render(request));
    }

    public Result create(Http.Request request){
        return ok(views.html.users.create.render());
    }

    public Result store(Http.Request request){
        User user = formFactory.form(User.class).bindFromRequest(request).get();

        System.out.println(user.fullname);
        System.out.println(user.password);
        System.out.println(user.username);

        return redirect(routes.UserController.index()).flashing("success","user has been inserted successfully!");
    }
}
