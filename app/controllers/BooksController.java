package controllers;

import io.ebean.DB;
import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import javax.inject.Inject;
import java.util.List;

public class BooksController extends Controller {

    @Inject
    FormFactory formFactory;



    public Result index(Http.Request request) {
        List<Book> books = DB.find(Book.class).findList();

        System.out.print(request.session().data());

        return ok(views.html.books.index.render(books, request));
    }

    public Result show(Double bookId) {
        Book bk = DB.find(Book.class, bookId);
        return ok(views.html.books.show.render(bk));
    }

    public Result create(Http.Request request){
        Form<Book> bookForm = formFactory.form(Book.class);
        return ok(views.html.books.create.render(request, bookForm));
    }

    public Result store(Http.Request request) {
        Form<Book> form = formFactory.form(Book.class).bindFromRequest(request);
        if(form.hasErrors()){
            return badRequest(views.html.books.create.render(request,form));
        }
        Book b = form.get();
        b.save();
        return redirect(routes.BooksController.index()).flashing("success","Book has been added successfully!");
    }

    public Result edit(Double bookId, Http.Request request) {
        Form<Book> bookForm = formFactory.form(Book.class);
        Book b = DB.find(Book.class, bookId);
        return ok(views.html.books.edit.render(b, request, bookForm));
    }

    public Result update(Double bookId, Http.Request request) {
        Book bk = DB.find(Book.class, bookId);
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest(request);

        if(bookForm.hasErrors()){
            return badRequest(views.html.books.edit.render(bk,request,bookForm));
        }

        Book b = bookForm.get();

        bk.title = b.title;
        bk.thumbnail = b.thumbnail;
        DB.update(bk);

        return redirect(routes.BooksController.index()).flashing("success","Book has been updated successfully!");
    }

    public Result delete(Double bookId) {
        DB.delete(Book.class,bookId);
        return redirect(routes.BooksController.index()).flashing("success","Book has been deleted successfully!");
    }
}