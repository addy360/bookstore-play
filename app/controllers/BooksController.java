package controllers;

import models.Book;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import javax.inject.Inject;
import java.util.ArrayList;

public class BooksController extends Controller {

    @Inject
    FormFactory formFactory;


    private Book book;

    public BooksController() {
        super();
        book = new Book();
    }

    public Result index() {
        ArrayList<Book> books = this.book.find();

        return ok(views.html.books.index.render(books));
    }

    public Result show(Double bookId) {
        Book b = this.book.findById(bookId);
        return ok(views.html.books.show.render(b));
    }

    public Result create(){
        Form<Book> form = formFactory.form(Book.class);
        return ok(views.html.books.create.render(form));
    }

    public Result store(Http.Request request) {
        Form<Book> form = formFactory.form(Book.class);
        Book b = form.bindFromRequest(request).get();
        book.save(b);
        return redirect("/books");
    }

    public Result edit(Double bookId) {
        Book b = this.book.findById(bookId);
        return ok(views.html.books.edit.render(b));
    }

    public Result update(Double bookId, Http.Request request) {
        DynamicForm updateForm = formFactory.form().bindFromRequest(request);

        Book b = this.book.findById(bookId);
        String title = updateForm.get("title");
        String thumbnail = updateForm.get("thumbnail");
        b.title = title;
        b.thumbnail = thumbnail;

        this.book.update(b);

        System.out.println(request.toString());
        return redirect("/books");
    }

    public Result delete(Double bookId) {
        this.book.findByIdAndDelete(bookId);
        return redirect("/books");
    }
}