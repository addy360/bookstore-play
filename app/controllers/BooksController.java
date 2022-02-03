package controllers;

import play.mvc.*;

public class BooksController extends Controller{

    public  Result index(){
        return ok("all books");
    }

    public Result show(int bookId) {
        System.out.println(bookId);
        return ok("Show book");
    }

    public Result store(Http.Request request){
        System.out.println(request.toString());
        return ok("store /post book");
    }

    public Result edit(int bookId){
        System.out.println(bookId);
        return ok("edit book");
    }

    public Result update(int bookId, Http.Request request){
        System.out.println(bookId);
        System.out.println(request.toString());
        return ok("updating book");
    }

    public Result delete(int bookId){
        System.out.println(bookId);
        return ok("deleting book");
    }
}