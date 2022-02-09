package actionMiddlewares;

import controllers.routes;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class AuthMiddleware extends Action.Simple {
    @Override
    public CompletionStage<Result> call(Http.Request req) {
        try{
            double userId = Double.parseDouble(req.session().get("uid").get()) ;
            // TODO: might check if user is valid and session has not expired yet
            return delegate.call(req);
        }catch (Exception e){
            // TODO: pass intended route to redirect after success login
            return CompletableFuture.completedFuture(redirect(routes.AuthController.login()));
        }

    }
}
