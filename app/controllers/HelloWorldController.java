package controllers;

import play.mvc.*;

public class HelloWorldController extends Controller {

    public Result index() {
        return ok("Hello, World!");
    }

}
