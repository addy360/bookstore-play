package controllers;

import io.ebean.DB;
import models.Book;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class BooksController extends Controller {

    @Inject
    FormFactory formFactory;


    private Book book;

    public BooksController() {
        super();
        book = new Book();
    }

    public Result index(Http.Request request) {
        List<Book> books = DB.find(Book.class).findList();

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
        Book b = DB.find(Book.class, bookId);
        return ok(views.html.books.edit.render(b, request));
    }

    public Result update(Double bookId, Http.Request request) {
        DynamicForm updateForm = formFactory.form().bindFromRequest(request);

        Book bk = DB.find(Book.class, bookId);
        String title = updateForm.get("title");
        String thumbnail = updateForm.get("thumbnail");

        bk.title = title;
        bk.thumbnail = thumbnail;
        DB.update(bk);

        return redirect(routes.BooksController.index()).flashing("success","Book has been updated successfully!");
    }

    public Result delete(Double bookId) {
        DB.delete(Book.class,bookId);
        return redirect(routes.BooksController.index()).flashing("success","Book has been deleted successfully!");
    }
}