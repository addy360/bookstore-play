package actionMiddlewares;

import controllers.routes;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class LoginMiddleware extends Action.Simple{
    @Override
    public CompletionStage<Result> call(Http.Request req) {
        try{
            double uid = Double.parseDouble(req.session().get("uid").get());
            return CompletableFuture.completedFuture(redirect(routes.BooksController.index()));
        }catch (Exception e){
            return delegate.call(req);
        }
    }
}
